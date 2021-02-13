SELECT q.*, q.next_start_time - q.finish_time AS timeout FROM
(SELECT f.title, s.start_time AS start_time, f.duration AS duration, 
 		s.start_time + f.duration * INTERVAL '1' MINUTE AS finish_time,
	    LEAD(start_time, 1) OVER (ORDER BY s.id) next_start_time
FROM cinema.sessions s JOIN cinema.films f on (s.film_id = f.id) 
ORDER BY s.id ASC) q
WHERE q.next_start_time - q.finish_time > '00:05'
ORDER BY timeout DESC 
