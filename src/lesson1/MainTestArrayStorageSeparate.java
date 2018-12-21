package lesson1;

public class MainTestArrayStorageSeparate {
    private static final ArrayStorageImpl TEST_ARRAY_STORAGE = new ArrayStorageImpl();

    private static final int SIZE = 5;
    private static boolean error;

    private static String red = "\u001B[31m";
    private static String green = "\u001B[32m";
    private static String black = "\u001B[30m";

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        testSave();

        System.out.println("TEST REPORT");
        System.out.println("=======================================");
        System.out.println(sb.toString());
        System.out.println("=======================================");
    }

    private static void testSave() {
        Resume[] testStorage = fillData();
        clearData();
        Resume[] storage = TEST_ARRAY_STORAGE.storage;
        int sizeStorage = 0;

        for (int i = 0; i < SIZE; i++) {
            Resume resume = new Resume();
            resume.uuid = "uuid_" + i;
            TEST_ARRAY_STORAGE.save(resume);

            if (storage[i] == null || !storage[i].uuid.equals(testStorage[i].uuid)) {
                formatMessages("Test save was complete with error - RESUME NOT EXIST!", red);
                break;
            } else {
                sizeStorage++;
            }

            if (sizeStorage != TEST_ARRAY_STORAGE.size) {
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
            formatMessages("Test save was complete successful!", green);
        }
    }

    private static void beforeTest() {
        error = false;
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
        TEST_ARRAY_STORAGE.storage = new Resume[10_000];
    }

    private static void formatMessages(String message, String color) {
        sb.append(color).append(message).append(black).append("\n");
        error = true;
    }
}
