package library_project.lab.model.helpers;

public class SequenceInventarenBroj {
    private static SequenceInventarenBroj instance = new SequenceInventarenBroj();

    private volatile Long next = 1l;

    private SequenceInventarenBroj() {
        // prevent external instantiation of a singleton.
    }

    public static SequenceInventarenBroj getInstance() {
        return instance;
    }

    public synchronized Long getNextSequence() {
        return next++;
    }
}
