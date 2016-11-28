import com.sun.org.apache.regexp.internal.RE;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Integer storageMaxLength = 10000;
    Resume[] storage = new Resume[storageMaxLength];
    private int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        if (isFull()){
            System.out.println("Хранилище резюме переполнено!!!");
            return;
        }
        if (find(r.getUuid()) != -1) {
            System.out.println("Извините, такое " + r + " резюме уже есть!!!");
            return;
        }
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        if (isNull())
            return null;
        int findUuid = find(uuid);
        if (findUuid != -1)
            return storage[findUuid];
        System.out.println("Такого " + uuid + " резюме нет!!!");
        return null;
    }

    void delete(String uuid) {
        if (isNull())
            return;
        int findUuid = find(uuid);
        if (findUuid != -1){
            storage[findUuid] = storage[size - 1];
            size--;
            return;
        }
        System.out.println("Такого " + uuid + " резюме нет!!!");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    int size() {
        return size;
    }

    private boolean isFull(){
        if (size == storageMaxLength)
            return true;
        else
            return false;
    }

    private boolean isNull() {
        if (size == 0){
            System.out.println("Хранилище резюме пустое!!!");
            return true;
        }
        return false;
    }

    private int find(String currentUuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(currentUuid)) {
                return i;
            }
        }
        return -1;
    }
}
