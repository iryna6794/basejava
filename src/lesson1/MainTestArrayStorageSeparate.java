package lesson1;

import java.util.Arrays;

public class MainTestArrayStorageSeparate {
    private static final ArrayStorageImpl TEST_ARRAY_STORAGE = new ArrayStorageImpl();

    private static final int SIZE = 5;

    private static String colorRed = "\u001B[31m";
    private static String colorGreen = "\u001B[32m";
    private static String colorBlack = "\u001B[30m";

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
        int sizeStorage = 0;
        int countMistakes = 0;
        final Resume[] storage = TEST_ARRAY_STORAGE.storage;

        for (int i = 0; i < SIZE; i++) {
            Resume resume = new Resume();
            resume.uuid = "uuid_" + i;
            TEST_ARRAY_STORAGE.save(resume);

            if (!testStorage[i].uuid.equals(storage[i].uuid)) {
                formatMessages("Test save was complete with error - RESUME NOT EXIST!", colorRed);
                countMistakes++;
                break;
            } else {
                sizeStorage++;
            }

            if (sizeStorage != TEST_ARRAY_STORAGE.size) {
                formatMessages("Test save was complete with error - SIZE!", colorRed);
                countMistakes++;
                break;
            }
        }

        for (int i = SIZE; i < storage.length; i++) {
            if (storage[i] != null) {
                formatMessages("Test save was complete with error!", colorRed);
                countMistakes++;
                break;
            }
        }

        if (countMistakes == 0) {
            formatMessages("Test save was complete successful!", colorGreen);
        }
    }

    private static void beforeTest() {
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
        if (color.equals(colorGreen)) {
            sb.append(color).append(message).append(colorBlack).append("\n");
        } else {
            sb.append(color).append(message).append(colorBlack).append("\n");
        }
    }
}
