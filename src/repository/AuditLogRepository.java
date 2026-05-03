package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.AuditEntry;

public class AuditLogRepository {
    private final List<AuditEntry> entries = new ArrayList<>();

    public void record(String actor, String action, String result) {
        entries.add(new AuditEntry(actor, action, result));
    }

    public List<AuditEntry> findAll() {
        return Collections.unmodifiableList(entries);
    }
}
