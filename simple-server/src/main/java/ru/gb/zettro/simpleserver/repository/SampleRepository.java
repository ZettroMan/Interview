package ru.gb.zettro.simpleserver.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class SampleRepository {
    private final Map<Long, SampleUnit> unitMap = new ConcurrentHashMap<>();

    private final AtomicLong idCounter = new AtomicLong(0);

    private final static SampleRepository sampleRepository = new SampleRepository();

    static {
        sampleRepository.saveOrUpdate(new SampleUnit(null, "Module 1", "Java syntax"));
        sampleRepository.saveOrUpdate(new SampleUnit(null, "Module 2", "Variables and Arrays"));
        sampleRepository.saveOrUpdate(new SampleUnit(null, "Module 3", "Standard I/O"));
        sampleRepository.saveOrUpdate(new SampleUnit(null, "Module 4", "Java classes"));
        sampleRepository.saveOrUpdate(new SampleUnit(null, "Module 5", "Derivation and Polymorphism"));
        sampleRepository.saveOrUpdate(new SampleUnit(null, "Module 6", "Exception handling"));
        sampleRepository.saveOrUpdate(new SampleUnit(null, "Module 7", "Collections"));
        sampleRepository.saveOrUpdate(new SampleUnit(null, "Module 8", "Multithreading"));
        sampleRepository.saveOrUpdate(new SampleUnit(null, "Module 9", "Stream API"));
        sampleRepository.saveOrUpdate(new SampleUnit(null, "Module 10", "Popular Frameworks"));
    }

    private SampleRepository() {
    }

    public static SampleRepository getInstance() {
        return sampleRepository;
    }

    public SampleUnit saveOrUpdate(SampleUnit unit) {
        if (unit.getId() == null || unit.getId() == -1L) {
            unit.setId(idCounter.incrementAndGet());
        }
        unitMap.put(unit.getId(), unit);
        return unit;
    }

    public SampleUnit findById(Long id) {
        return unitMap.get(id);
    }

    public List<SampleUnit> findAll() {
        return new ArrayList<>(unitMap.values());
    }

    public SampleUnit deleteById(Long id) {
        return unitMap.remove(id);
    }

}
