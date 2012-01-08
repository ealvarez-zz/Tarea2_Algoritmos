public class Test {

    private int k;
    private int n;
    private int max;
    private int size;
    private int[] instance;
    private Tarea2Structure structure;
    private InstanceFactory instanceGenerator;

    public Test(int n, int max, Tarea2Structure structure) {
        this.n = n;
        this.max = max;
        this.size = 3 * n * k;
        this.instanceGenerator = new InstanceFactory();
        this.structure = structure;
    }

    public long[] runRandom(int k) {
        this.k = k;
        this.instance = this.instanceGenerator.createRandomInstance(this.max, this.size);
        return run(k);
    }

    public long[] runSemiOrdenered(int k) {
        this.k = k;
        this.instance = this.instanceGenerator.createSemiOrdeneredInstance(this.max, this.size);
        return run(k);
    }

    private long[] run(int k) {

        long firstTest = 0;
        long secondTest = 0;


        for (int i = 0; i < this.n; i++) {

            firstTest = System.nanoTime();

            for (int j = 0; j < k; j++) {
                this.structure.insert(i);
            }
            for (int j = 0; j < k; j++) {
                this.structure.deleteMin();
            }
            for (int j = 0; j < k; j++) {
                this.structure.insert(i);
            }

            firstTest = System.nanoTime() - firstTest;
        }



        for (int i = 0; i < this.n; i++) {

            secondTest = System.nanoTime();

            for (int j = 0; j < k; j++) {
                this.structure.deleteMin();
            }
            for (int j = 0; j < k; j++) {
                this.structure.insert(i);
            }
            for (int j = 0; j < k; j++) {
                this.structure.deleteMin();
            }

            secondTest = System.nanoTime() - secondTest;
        }


        return new long[]{firstTest / this.n, secondTest / this.n};
    }
}
