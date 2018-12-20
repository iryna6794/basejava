import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private static final ArrayStorageImpl TEST_ARRAY_STORAGE = new ArrayStorageImpl();
    private static final int SIZE = 10;

    public static void main(String[] args) {

        //test save(Resume resume)
        for (int i = 0; i < SIZE; i++) {
            Resume resume = new Resume();
            resume.uuid = String.valueOf(i);
            TEST_ARRAY_STORAGE.save(resume);

            if (!TEST_ARRAY_STORAGE.get(String.valueOf(i)).uuid.equals(String.valueOf(i))) {
                System.out.println("\u001B[31m" + "Метод получения или сохранения резюме реализован с ошибкой" + "\u001B[30m");
                return;
            }

            if (TEST_ARRAY_STORAGE.size() != i + 1) {
                System.out.println("\u001B[31m" + "Ошибка при сохранении, размер хранилища не соответствует ожидаемому" + "\u001B[30m");
                return;
            }

            if (TEST_ARRAY_STORAGE.get("test") != null) {
                System.out.println("\u001B[31m" + "В тесте получения резюме ошибка" + "\u001B[30m");
                return;
            }
        }

        printAll("Резюме в хранилище после сохранения");

        //test delete(String uuid))
        for (int i = 0; i < 5; i++) {
            TEST_ARRAY_STORAGE.delete(String.valueOf(i));

            if (TEST_ARRAY_STORAGE.get(String.valueOf(i)) != null) {
                System.out.println("\u001B[31m" + "Резюме не удалено!" + "\u001B[30m");
                return;
            }

            if (TEST_ARRAY_STORAGE.size() != SIZE - i - 1) {
                System.out.println("\u001B[31m" + "Ошибка при удалении, размер хранилища не соответствует ожидаемому" + "\u001B[30m");
                return;
            }
        }

        printAll("Резюме в хранилище после удаления");

        //test getAll()
        Resume[] allResumes = TEST_ARRAY_STORAGE.getAll();

        if (allResumes.length != TEST_ARRAY_STORAGE.size()) {
            System.out.println("\u001B[31m" + "Ошибка получения списка резюме" + "\u001B[30m");
        }

        for (Resume resume : allResumes) {
            if (TEST_ARRAY_STORAGE.get(resume.uuid) == null) {
                System.out.println("\u001B[31m" + "Получен не полный список резюме" + "\u001B[30m");
            }
        }

        printAll("Резюме в хранилище после получения списка резюме");

        //test clear()
        //Attention this is Reflection API!
        try {
            TEST_ARRAY_STORAGE.clear();
            Field field = TEST_ARRAY_STORAGE.getClass().getDeclaredField("storage");
            field.setAccessible(true);
            Resume[] reflectionStorage = (Resume[]) field.get(TEST_ARRAY_STORAGE);

            for (Resume resume : reflectionStorage) {
                if (resume != null) {
                    System.out.println("\u001B[31m" + "Хранилище не очищено!" + "\u001B[30m");
                    System.out.println(Arrays.toString(reflectionStorage));
                    return;
                }
            }

            if (TEST_ARRAY_STORAGE.size() != 0) {
                System.out.println("\u001B[31m" + "Ошибка очищения, размер хранилища не соответствует ожидаемому!" + "\u001B[30m");
                return;
            }

            printAll("Резюме в хранилище после очистки");
        } catch (Exception e) {
            System.out.println("\u001B[31m" + "Назовите хранилище - storage или исправьте тест :)" + "\u001B[30m");
        }
    }

    static void printAll(String message) {
        System.out.println(message);

        for (Resume r : TEST_ARRAY_STORAGE.getAll()) {
            System.out.print(r + " ");
        }

        System.out.println("\n");
    }
}
