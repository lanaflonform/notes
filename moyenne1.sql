select e.MATRICULE, e.NOM, SUM(n.VALEUR * ed.POURCENTAGE) / SUM(ed.POURCENTAGE) as MOYENNE
from ETUDIANT e, NOTE n, COURS c, TYPECOURS tc, EVALUATION ee, EVALUATIONDETAILS ed
where e.ID = n.ETUDIANT_ID and ee.ID = n.EVALUATION_ID and n.COURS_ID = c.ID and c.TYPECOURS_ID = tc.ID
and ed.TYPECOURS_ID = tc.ID and ed.EVALUATION_ID = ee.ID and c.ID = 14 and (not ee.ISEXAM or n.sessions = 0)
group by e.MATRICULE
having sum(ed.POURCENTAGE) = 100
order by e.NOM
