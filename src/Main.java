public class Main {
    private double[] weights;
    private double bias; // смещение
    private double learningSpeed; // скорость обучения

    // Конструктор, инициализирующий веса и смещение
    public Main() {
        weights = new double[2];  // У нас 2 входа для каждой логической функции
        bias = 0;
        learningSpeed = 0.1;
    }

    // Метод активации перцептрона (пороговая функция)
    public double activate(double[] inputs) {
        double sum = 0;
        for (int i = 0; i < inputs.length; i++) {
            sum += inputs[i] * weights[i];
        }
        sum += bias;
        return sum > 0 ? 1 : 0; // Возвращаем 1, если сумма больше 0, иначе 0
    }

    // Обучение перцептрона с использованием заданных входов и правильных ответов
    public void train(double[][] inputs, double[] outputs, int epochs) {
        for (int epoch = 0; epoch < epochs; epoch++) {
            for (int i = 0; i < inputs.length; i++) {
                double output = activate(inputs[i]); // Получаем предсказание
                double error = outputs[i] - output; // Вычисляем ошибку

                // Обновляем веса и смещение по правилу обучения
                for (int j = 0; j < weights.length; j++) {
                    weights[j] += learningSpeed * error * inputs[i][j];
                }
                bias += learningSpeed * error;
            }
        }
    }

    // Метод для логической функции "И"
    public void trainAND() {
        double[][] inputs = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
        double[] outputs = { 0, 0, 0, 1 };
        train(inputs, outputs, 1000); // Обучение на 1000 эпох
    }

    // Метод для логической функции "ИЛИ"
    public void trainOR() {
        double[][] inputs = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
        double[] outputs = { 0, 1, 1, 1 };
        train(inputs, outputs, 1000); // Обучение на 1000 эпох
    }

    // Метод для логической функции "НЕ" для одного входа
    public void trainNOT() {
        double[][] inputs = { { 0 }, { 1 } };
        double[] outputs = { 1, 0 };
        weights = new double[1]; // Здесь только один вход
        bias = 0;
        train(inputs, outputs, 1000); // Обучение на 1000 эпох
    }

    // Основной метод
    public static void main(String[] args) {
        Main perceptron = new Main();

        // Обучаем для логической функции "И"
        perceptron.trainAND();
        System.out.println("AND: ");
        System.out.println("0 AND 0 = " + perceptron.activate(new double[] { 0, 0 }));
        System.out.println("0 AND 1 = " + perceptron.activate(new double[] { 0, 1 }));
        System.out.println("1 AND 0 = " + perceptron.activate(new double[] { 1, 0 }));
        System.out.println("1 AND 1 = " + perceptron.activate(new double[] { 1, 1 }));

        // Обучаем для логической функции "ИЛИ"
        perceptron.trainOR();
        System.out.println("\nOR: ");
        System.out.println("0 OR 0 = " + perceptron.activate(new double[] { 0, 0 }));
        System.out.println("0 OR 1 = " + perceptron.activate(new double[] { 0, 1 }));
        System.out.println("1 OR 0 = " + perceptron.activate(new double[] { 1, 0 }));
        System.out.println("1 OR 1 = " + perceptron.activate(new double[] { 1, 1 }));

        // Обучаем для логической функции "НЕ"
        perceptron.trainNOT();
        System.out.println("\nNOT: ");
        System.out.println("NOT 0 = " + perceptron.activate(new double[] { 0 }));
        System.out.println("NOT 1 = " + perceptron.activate(new double[] { 1 }));
    }
}
