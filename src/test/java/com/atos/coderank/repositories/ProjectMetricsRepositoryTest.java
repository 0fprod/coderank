package com.atos.coderank.repositories;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.atos.coderank.entities.ProjectEntity;
import com.atos.coderank.entities.ProjectMetricsEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProjectMetricsRepositoryTest {

	@Autowired
	private TestEntityManager em;

	@Autowired
	private ProjectMetricsRepository pmr;

	@Test
	public void whenFindMostRecent_thenReturnProjectMetrics() {
		ProjectMetricsEntity pme1 = new ProjectMetricsEntity();
		ProjectMetricsEntity pme2 = new ProjectMetricsEntity();
		ProjectMetricsEntity pme3 = new ProjectMetricsEntity();

		pme1.setSizLines(5);
		pme2.setSizLines(51);
		pme3.setSizLines(7);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			pme1.setVersionDate(sdf.parse("10/05/1990"));
			pme2.setVersionDate(sdf.parse("05/09/2009"));
			pme3.setVersionDate(sdf.parse("03/07/2010"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		ProjectEntity p1 = new ProjectEntity();
		ProjectEntity p2 = new ProjectEntity();

		p1.setProjectId("id1");
		p2.setProjectId("id2");

		pme1.setProject(p1);
		pme2.setProject(p2);
		pme3.setProject(p2);

		em.persistAndFlush(p1);
		em.persistAndFlush(p2);
		em.persistAndFlush(pme1);
		em.persistAndFlush(pme2);
		em.persistAndFlush(pme3);

		List<ProjectMetricsEntity> all = this.pmr.findAll();
		System.out.println("#################");
		System.out.println("#################");
		for (ProjectMetricsEntity e : all) {
			System.out.println(e.getMetricsId() + ", " + sdf.format(e.getVersionDate()) + " -> " + e.getProject().getProjectId());
		}
		System.out.println("#################");
		System.out.println("#################");

		List<ProjectMetricsEntity> list = this.pmr.findMostRecents();
		System.out.println("#################");
		System.out.println("#################");
		for (ProjectMetricsEntity e : list) {
			System.out.println(e.getMetricsId() + ", " + sdf.format(e.getVersionDate()) + " -> " + e.getProject().getProjectId());
		}
		System.out.println("#################");
		System.out.println("#################");

		assertEquals(2, list.size());

	}

	@Test
	public void whenFindMostRecentById_thenReturnProjectMetrics() {
		ProjectMetricsEntity pme1 = new ProjectMetricsEntity();
		ProjectMetricsEntity pme2 = new ProjectMetricsEntity();
		ProjectMetricsEntity pme3 = new ProjectMetricsEntity();

		pme1.setSizLines(5);
		pme2.setSizLines(51);
		pme3.setSizLines(7);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			pme1.setVersionDate(sdf.parse("10/05/1990"));
			pme2.setVersionDate(sdf.parse("05/09/2009"));
			pme3.setVersionDate(sdf.parse("03/07/2010"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		ProjectEntity p1 = new ProjectEntity();
		ProjectEntity p2 = new ProjectEntity();

		p1.setProjectId("id1");
		p2.setProjectId("id2");

		pme1.setProject(p1);
		pme2.setProject(p2);
		pme3.setProject(p2);

		em.persistAndFlush(p1);
		em.persistAndFlush(p2);
		em.persistAndFlush(pme1);
		em.persistAndFlush(pme2);
		em.persistAndFlush(pme3);

		ProjectMetricsEntity found = this.pmr.findMostRecentByProjectId(p2.getProjectId());

		assertEquals(pme3, found);

	}
	
}
