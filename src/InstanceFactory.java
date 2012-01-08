import java.util.Random;
import java.util.Arrays;

/**
*
* @author Erwin Alvarez C <ealvarez at dcc.uchile.cl>
*/
public class InstanceFactory {

    private Random _randomGenerator;

    public InstanceFactory() {
        this._randomGenerator = new Random();
    }

    public int[] createRandomInstance(int max, int size) {

        int[] instance = new int[size];

        for (int i = 0; i < size; i++) {
            instance[i] = this._randomGenerator.nextInt(max);
        }

        return instance;
    }

    public int[] createSemiOrdeneredInstance(int max, int size) {

        int[] instance = this.createRandomInstance(max, size);

        Arrays.sort(instance);

        int swapCounter = size / 4;
        int j = 0;
        int k = 0;

        for (int i = 0; i < swapCounter; i++) {
            j = this._randomGenerator.nextInt(size);
            k = this._randomGenerator.nextInt(size);
            this.swap(j, k, instance);
        }

        return instance;
    }

    private void swap(int index1, int index2, int[] instance) {
        int elementHolder = instance[index1];
        instance[index1] = instance[index2];
        instance[index2] = elementHolder;
    }
}