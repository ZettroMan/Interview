SELECT COALESCE(f.title, 'Итого:') AS title, COUNT(t.id) AS viewers, COUNT(DISTINCT s.id) AS sessions_count, COALESCE(SUM(price),0) AS total_income, 
	COALESCE(COUNT(*) / NULLIF(COUNT(DISTINCT s.id), 0), 0) AS average_viewers  FROM cinema.sessions s
JOIN cinema.tickets t ON t.session_id = s.id
RIGHT JOIN cinema.films f on s.film_id = f.id
GROUP BY ROLLUP(title)
ORDER BY total_income DESC 