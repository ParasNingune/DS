import mpi.*;

public class MPISum {

    public static void main(String[] args)
            throws Exception {

        // Initialize MPI
        MPI.Init(args);

        // Processor rank
        int rank = MPI.COMM_WORLD.Rank();

        // Total processors
        int size = MPI.COMM_WORLD.Size();

        // Array
        int[] arr =
                {10, 20, 30, 40, 50, 60, 70, 80};

        int n = arr.length;

        // Elements per processor
        int elements = n / size;

        int start = rank * elements;
        int end = start + elements;

        int localSum = 0;

        // Calculate local sum
        for (int i = start; i < end; i++) {

            localSum += arr[i];
        }

        // Display processor computation
        System.out.println(
                "Processor " + rank
                        + " calculated local sum = "
                        + localSum);

        int[] globalSum = new int[1];

        // Reduce operation
        MPI.COMM_WORLD.Reduce(
                new int[]{localSum},
                0,
                globalSum,
                0,
                1,
                MPI.INT,
                MPI.SUM,
                0);

        // Root processor prints final sum
        if (rank == 0) {

            System.out.println(
                    "\nFinal Sum = "
                            + globalSum[0]);
        }

        // Finalize MPI
        MPI.Finalize();
    }
}