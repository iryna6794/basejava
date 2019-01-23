package lesson1_tanya;

public class ArrayStorage {

    Resume[] storage = new Resume[10_000];
    int size;

    void save(Resume resume) {
    }

    Resume get(String uuid) {
        return null;
    }

    void delete(String uuid) {
    }

    void clear() {
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return new Resume[0];
    }

    int size() {
        return 0;
    }

    //    use this method only for testing
    public Resume[] getStorage() {
        return storage;
    }

    //    use this method only for testing
    public void setSize(int size) {
       this.size = size;
    }
}