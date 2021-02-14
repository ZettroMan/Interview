SELECT COALESCE(start_period, 'Итого:') AS time_interval, SUM(price) AS total_income, COUNT(ticket_id) AS viewers FROM (
SELECT f.title, s.start_time AS start_time, f.duration AS duration, 
 		s.start_time + f.duration * INTERVAL '1' MINUTE AS finish_time,
		s.price, t.id as ticket_id,
		CASE
  		  WHEN s.start_time >= '00:00:00' AND s.start_time < '09:00:00' THEN '00:00 - 09:00'
  		  WHEN s.start_time >= '09:00:00' AND s.start_time < '15:00:00' THEN '09:00 - 15:00'
  		  WHEN s.start_time >= '15:00:00' AND s.start_time < '18:00:00' THEN '15:00 - 18:00'
  		  WHEN s.start_time >= '18:00:00' AND s.start_time < '21:00:00' THEN '18:00 - 21:00'
  		  WHEN s.start_time >= '21:00:00' AND s.start_time <= '23:59:59' THEN '21:00 - 24:00'
		END AS start_period
		FROM cinema.sessions s
JOIN cinema.tickets t ON t.session_id = s.id
JOIN cinema.films f on s.film_id = f.id) q
GROUP BY ROLLUP(start_period)
ORDER BY start_period
