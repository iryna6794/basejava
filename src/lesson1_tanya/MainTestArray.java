package lesson1_tanya;

import java.util.Arrays;

public class MainTestArray {

    private static ArrayStorageImpl arrayStorage;
    private static Resume[] storage;
    private static int initSize = 5;

    private static final Resume RESUME1 = new Resume();
    private static final Resume RESUME2 = new Resume();
    private static final Resume RESUME3 = new Resume();
    private static final Resume RESUME4 = new Resume();
    private static final Resume RESUME5 = new Resume();

    static {
        RESUME1.uuid = "uuid1";
        RESUME2.uuid = "uuid2";
        RESUME3.uuid = "uuid3";
        RESUME4.uuid = "uuid4";
        RESUME5.uuid = "uuid5";
    }

    public static void main(String[] args) {

        testSave();
        testGet();
        testDelete();
        testGetAll();
        testSize();
        testClear();

    }


    private static void testSave() {
        fillData();

        Resume resumeForSave = new Resume();
        resumeForSave.uuid = "uuid6";
        arrayStorage.save(resumeForSave);

        for (int i = 0; i <= initSize; i++) {
            if (!storage[i].uuid.equals("uuid" + (i + 1))) {
                System.out.println("method save contains mistakes");
                break;
            }
        }

        for (int i = initSize + 1; i < storage.length; i++) {
            if (storage[i] != null) {
                System.out.println("method save contains mistakes");
                break;
            }
        }

        if (arrayStorage.size() != initSize + 1) {
            System.out.println("method save contains mistakes");
        }
    }

    private static void testGet() {
        fillData();

        for (int i = 1; i <= initSize; i++) {
            Resume resume = arrayStorage.get("uuid" + i);

            if(resume == null){
                System.out.println("method get contains mistakes");
                break;
            }

            if (!resume.uuid.equals("uuid" + i)) {
                System.out.println("method get contains mistakes");
                break;
            }
        }

        if (arrayStorage.size() != initSize) {
            System.out.println("method get contains mistakes");
        }
    }

    private static void testDelete() {
        fillData();
        String uuidForDelete = "uuid1";
        arrayStorage.delete(uuidForDelete);

        for (Resume resume : storage) {
            if (resume != null && resume.uuid.equals(uuidForDelete))
                System.out.println("method delete contains mistakes");
        }

        if (arrayStorage.size() != initSize - 1 || storage[initSize - 1] != null) {
            System.out.println("method delete contains mistakes");
        }
    }

    private static void testGetAll() {
        fillData();
        Resume[] resumes = new Resume[]{RESUME1, RESUME2, RESUME3, RESUME4, RESUME5};

        if (!Arrays.equals(resumes, arrayStorage.getAll())) {
            System.out.println("method getAll contains mistakes");
        }
    }

    private static void testSize() {
        fillData();
        if (arrayStorage.size() != initSize) {
            System.out.println("method size contains mistakes");
        }
    }

    private static void testClear() {
        fillData();
        arrayStorage.clear();

        for (Resume resume : storage) {
            if (resume != null) {
                System.out.println("method clear contains mistakes");
                break;
            }
        }

        if (arrayStorage.size() != 0) {
            System.out.println("method clear contains mistakes");
        }
    }

    private static void fillData() {
        arrayStorage = new ArrayStorageImpl();
        storage = arrayStorage.getStorage();

        storage[0] = RESUME1;
        storage[1] = RESUME2;
        storage[2] = RESUME3;
        storage[3] = RESUME4;
        storage[4] = RESUME5;

        arrayStorage.setSize(initSize);
    }
}