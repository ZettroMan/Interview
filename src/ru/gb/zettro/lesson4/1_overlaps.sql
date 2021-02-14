SELECT q.film_name, q.start_time, q.film_duration, q.finish_time, f.title as next_film_name, q.next_start_time, 
       f.duration as next_film_duration, q.finish_time - q.next_start_time as overlap FROM
(SELECT f.id, f.title as film_name, s.start_time as start_time, f.duration as film_duration, 
 		s.start_time + f.duration * INTERVAL '1' MINUTE as finish_time,
	    LEAD(start_time, 1) OVER (ORDER BY s.id) next_start_time,
	    LEAD(film_id, 1) OVER (ORDER BY s.id) next_film_id
FROM cinema.sessions s JOIN cinema.films f on (s.film_id = f.id) 
ORDER BY s.id ASC) q
JOIN cinema.films f on f.id = q.next_film_id 
WHERE finish_time > next_start_time
