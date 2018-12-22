package lesson1;

public class MainTestArrayStorageSeparate {
    private static final ArrayStorageImpl TEST_ARRAY_STORAGE = new ArrayStorageImpl();
    private static final int SIZE = 5;
    private static boolean error;
    private static String red = "\u001B[31m";
    private static String green = "\u001B[32m";
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        testSave();
        testGet();
        testDelete();
        testClear();
        testGetAll();
        testSize();

        System.out.println("TEST REPORT");
        System.out.println("=======================================");
        System.out.println(sb.toString());
        System.out.println("=======================================");
    }

    private static void testSave() {
        Resume[] testStorage = fillData();
        clearData();
        Resume[] storage = TEST_ARRAY_STORAGE.storage;
        int sizeTestStorage = 0;

        for (int i = 0; i < SIZE; i++) {
            Resume resume = new Resume();
            resume.uuid = "uuid_" + i;
            TEST_ARRAY_STORAGE.save(resume);

            if (storage[i] == null || !storage[i].uuid.equals(testStorage[i].uuid)) {
                formatMessages("Test save was complete with error - RESUME NOT EXIST!", red);
                break;
            } else {
                sizeTestStorage++;
            }

            if (sizeTestStorage != TEST_ARRAY_STORAGE.size) {
                formatMessages("Test save was complete with error - SIZE!", red);
                break;
            }
        }

        for (int i = SIZE; i < storage.length; i++) {
            if (storage[i] != null) {
                formatMessages("Test save was complete with error!", red);
                break;
            }
        }

        if (!error) {
            formatMessages("1: Test save was complete successful!", green);
        }
    }

    private static void testGet() {
        beforeTest();

        for (int i = 0; i < SIZE; i++) {
            Resume resume = TEST_ARRAY_STORAGE.get("uuid_" + i);
            if (resume == null || !resume.uuid.equals("uuid_" + i)) {
                formatMessages("Test get was complete with not exist error!", red);
                break;
            }
        }

        if (!error) {
            formatMessages("2: Test get was complete successful!", green);
        }
    }

    private static void testDelete() {
        beforeTest();

        for (int i = 0; i < SIZE; i++) {
            try {
                TEST_ARRAY_STORAGE.delete("uuid_" + i);
            } catch (NullPointerException e) {
                formatMessages("Test delete was complete with error!", red);
                break;
            }
        }

        for (int i = 0; i < TEST_ARRAY_STORAGE.storage.length; i++) {
            if (TEST_ARRAY_STORAGE.storage[i] != null) {
                formatMessages("Test delete was complete with error - RESUME WAS NOT DELETED!", red);
                break;
            }
        }

        if (TEST_ARRAY_STORAGE.size != 0) {
            formatMessages("Test delete was complete with error - SIZE!", red);
        }

        if (!error) {
            formatMessages("3: Test delete was complete successful!", green);
        }
    }

    private static void testClear() {
        beforeTest();
        TEST_ARRAY_STORAGE.clear();

        for (int i = 0; i < TEST_ARRAY_STORAGE.storage.length; i++) {
            if (TEST_ARRAY_STORAGE.storage[i] != null) {
                formatMessages("Test clear was complete with error - STORAGE CONTAINS RESUMES!", red);
                break;
            }
        }

        if (TEST_ARRAY_STORAGE.size != 0) {
            formatMessages("Test clear was complete with error - SIZE!", red);
        }

        if (!error) {
            formatMessages("4: Test clear was complete successful!", green);
        }
    }

    private static void testGetAll() {
        beforeTest();
        Resume[] allResumes = TEST_ARRAY_STORAGE.getAll();
        Resume[] testResumes = TEST_ARRAY_STORAGE.storage;

        if (allResumes.length != SIZE) {
            formatMessages("Test getAll was complete with error - SIZE!", red);
            return;
        }

        for (int i = 0; i < SIZE; i++) {
            if (allResumes[i].uuid == null || !allResumes[i].uuid.equals(testResumes[i].uuid)) {
                formatMessages("Test getAll was complete with error!", red);
                break;
            }
        }

        if (!error) {
            formatMessages("5: Test getAll was complete successful!", green);
        }
    }

    private static void testSize() {
        beforeTest();

        if (TEST_ARRAY_STORAGE.size() != SIZE) {
            formatMessages("Test size was complete with error - SIZE!", red);
        }

        if (!error) {
            formatMessages("6: Test size was complete successful!", green);
        }
    }

    private static void beforeTest() {
        error = false;
        TEST_ARRAY_STORAGE.size = SIZE;
        clearData();
        fillData();
    }

    private static Resume[] fillData() {
        for (int i = 0; i < SIZE; i++) {
            Resume resume = new Resume();
            resume.uuid = "uuid_" + i;
            TEST_ARRAY_STORAGE.storage[i] = resume;
        }
        return TEST_ARRAY_STORAGE.storage;
    }

    private static void clearData() {
        for (int i = 0; i < TEST_ARRAY_STORAGE.storage.length; i++) {
            TEST_ARRAY_STORAGE.storage[i] = null;
        }
    }

    private static void formatMessages(String message, String color) {
        String black = "\u001B[30m";
        sb.append(color).append(message).append(black).append("\n");
        error = true;
    }
}
