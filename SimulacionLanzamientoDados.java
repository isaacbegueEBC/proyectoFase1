public class SimulacionLanzamientoDados {
    public static void main(String[] args) {
        int numeroDeLanzamientos = 10;
        int[] resultados = new int[numeroDeLanzamientos];

        for (int i = 0; i < numeroDeLanzamientos; i++) {
            resultados[i] = lanzarDado();
        }

        System.out.println("Resultados de los lanzamientos de dado:");
        for (int i = 0; i < resultados.length; i++) {
            System.out.println("Lanzamiento " + (i + 1) + ": " + resultados[i]);
        }
    }

    public static int lanzarDado() {
        return (int) (Math.random() * 6) + 1;
    }
}
