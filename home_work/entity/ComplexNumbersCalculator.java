package home_work.entity;

import home_work.service.ComplexNumberService;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ComplexNumbersCalculator implements ComplexNumbersOperations{

    private static final Logger logger = Logger.getLogger(ComplexNumbersCalculator.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("calculator_logs.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.INFO);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка при настройке логгера", e);
        }
    }

    final ComplexNumberService service = new ComplexNumberService();

    @Override
    public ComplexNumber addict(ComplexNumber cNumber1, ComplexNumber cNumber2) {

        Instant startInstant = Instant.now();
        String startTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault())
                .format(startInstant);

        logger.log(Level.INFO, "Начало операции сложения: Время: {0}", startTime);

        logger.log(Level.INFO, "Выполенения сложения действительной части комплексных чисел: ({0} + {1})", new Object[]{cNumber1.getReal(), cNumber2.getReal()});
        service.getComplexNumber().setReal(cNumber1.getReal() + cNumber2.getReal());
        logger.log(Level.INFO, "Выполенения сложения мнимой части комплексных чисел: ({0} + {1})", new Object[]{cNumber1.getImaginary(), cNumber2.getImaginary()});
        service.getComplexNumber().setImaginary(cNumber1.getImaginary() + cNumber2.getImaginary());
        logger.log(Level.INFO, "В результате получаем комплексное число: ({0} + {1})", new Object[]{service.getComplexNumber().getReal(), service.getComplexNumber().getImaginary()});

        Instant endInstant = Instant.now();
        String endTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault())
                .format(endInstant);

        logger.log(Level.INFO, "Окончание операции сложения: Время: {0}", endTime);
        long duration = endInstant.toEpochMilli() - startInstant.toEpochMilli();

        logger.log(Level.INFO, "Затраченное время: {0} ms", duration);
        return service.getComplexNumber();

    }

    @Override
    public ComplexNumber substract(ComplexNumber cNumber1, ComplexNumber cNumber2) {

        Instant startInstant = Instant.now();
        String startTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault())
                .format(startInstant);

        logger.log(Level.INFO, "Начало операции вычитания: Время: {0}", startTime);
        logger.log(Level.INFO, "Выполенения вычитания действительной части комплексных чисел: ({0} - {1})", new Object[]{cNumber1.getReal(), cNumber2.getReal()});
        service.getComplexNumber().setReal(cNumber1.getReal() - cNumber2.getReal());
        logger.log(Level.INFO, "Выполенения вычитания мнимой части комплексных чисел: ({0} - {1})", new Object[]{cNumber1.getImaginary(), cNumber2.getImaginary()});
        service.getComplexNumber().setImaginary(cNumber1.getImaginary() - cNumber2.getImaginary());
        logger.log(Level.INFO, "В результате получаем комплексное число: ({0} + {1})", new Object[]{service.getComplexNumber().getReal(), service.getComplexNumber().getImaginary()});

        Instant endInstant = Instant.now();
        String endTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault())
                .format(endInstant);

        long duration = endInstant.toEpochMilli() - startInstant.toEpochMilli();

        logger.log(Level.INFO, "Окончание операции вычитания: Время: {0}", endTime);
        logger.log(Level.INFO, "Затраченное время: {0} ms", duration);
        return service.getComplexNumber();

    }

    @Override
    public ComplexNumber divide(ComplexNumber cNumber1, ComplexNumber cNumber2) {


        if(cNumber1.getImaginary() != 0 && cNumber2.getImaginary() != 0){

            Instant startInstant = Instant.now();
            String startTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneId.systemDefault())
                    .format(startInstant);

            logger.log(Level.INFO, "Начало выполнения деления комплексных чисел, когда присутствуют обе мнимые части: {0}", startTime);
            // Комплексно-сопряженное число
            logger.log(Level.INFO, "Создаем комплексно-сопряженное число");
            ComplexNumber complexConjugateNumber = new ComplexNumber();
            logger.log(Level.INFO, "Устанавливаем значение действительной части комплексно-сопряженного числа:  {0}", cNumber2.getReal());
            complexConjugateNumber.setReal(cNumber2.getReal());
            logger.log(Level.INFO, "Устанавливаем значение мнимой части комплексно-сопряженного числа:  {0}", -cNumber2.getImaginary());
            complexConjugateNumber.setImaginary(-cNumber2.getImaginary());

            logger.log(Level.INFO, "Вычисляем делитель на основе комплексно-сопряженного числа {0} и делителя {1}, на который будем делить комплексное число: {2}",new Object[]{complexConjugateNumber, cNumber2, cNumber1});
            logger.log(Level.INFO, "Действительная часть состоит из {0}^{1}, мнимая часть состоит из {2}^{3}", new Object[]{cNumber2.getReal(), 2, complexConjugateNumber.getImaginary(), 2});
            double denominator = Math.pow(cNumber2.getReal(), 2) + Math.pow(cNumber2.getImaginary(), 2);
            logger.log(Level.INFO, "Делитель: {0}", denominator);

            logger.log(Level.INFO, "Вычисляем действительную часть комплексного числа путем: (({0} * {1} + {2} * {3}) * -1) / {4}", new Object[]{cNumber1.getReal(), complexConjugateNumber.getReal(), cNumber1.getImaginary(), complexConjugateNumber.getImaginary(), denominator});
            service.getComplexNumber().setReal(((cNumber1.getReal() * complexConjugateNumber.getReal())
                                + ((cNumber1.getImaginary() * complexConjugateNumber.getImaginary()) * -1D))
                                / denominator);

            logger.log(Level.INFO, "Вычисляем мнимую часть комплексного числа путем: (({0} * {1} + {2} * {3}) * -1) / {4}", new Object[]{cNumber1.getReal(), 2, cNumber2.getImaginary(), complexConjugateNumber.getImaginary(), denominator});
            service.getComplexNumber().setImaginary(((cNumber2.getReal() * 2)
                                + ((cNumber2.getImaginary() * complexConjugateNumber.getImaginary())) * -1D)
                                / denominator);

            Instant endInstant = Instant.now();
            String endTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneId.systemDefault())
                    .format(endInstant);

            long duration = endInstant.toEpochMilli() - startInstant.toEpochMilli();

            logger.log(Level.INFO, "Окончание операции деления: Время: {0}", endTime);
            logger.log(Level.INFO, "Затраченное время: {0} ms", duration);

            logger.log(Level.INFO, "В результате получаем комплексное число {0}", service.getComplexNumber());
            return service.getComplexNumber();

        }else if(cNumber1.getImaginary() == 0){

            Instant startInstant = Instant.now();
            String startTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneId.systemDefault())
                    .format(startInstant);

            logger.log(Level.INFO, "Начало выполнения деления комплексных чисел, когда у первого числа нет мнимой части: {0}", startTime);
            // Комплексно-сопряженное число
            logger.log(Level.INFO, "Создаем комплексно-сопряженное число");
            ComplexNumber complexConjugateNumber = new ComplexNumber();
            logger.log(Level.INFO, "Устанавливаем значение действительной части комплексно-сопряженного числа:  {0}", cNumber2.getReal());
            complexConjugateNumber.setReal(cNumber2.getReal());
            logger.log(Level.INFO, "Устанавливаем значение мнимой части комплексно-сопряженного числа:  {0}", -cNumber2.getImaginary());
            complexConjugateNumber.setImaginary(-cNumber2.getImaginary());

            logger.log(Level.INFO, "Вычисляем делитель на основе комплексно-сопряженного числа {0} и делителя {1}, на который будем делить комплексное число: {2}",new Object[]{complexConjugateNumber, cNumber2, cNumber1});
            logger.log(Level.INFO, "Действительная часть состоит из {0}^{1}, мнимая часть состоит из {2}^{3}", new Object[]{cNumber2.getReal(), 2, complexConjugateNumber.getImaginary(), 2});
            double denominator = Math.pow(cNumber2.getReal(), 2) + Math.pow(cNumber2.getImaginary(), 2);
            logger.log(Level.INFO, "Делитель: {0}", denominator);

            logger.log(Level.INFO, "Вычисляем действительную часть комплексного числа путем: (({0} * {1} - {2} * {3})) / {4}", new Object[]{cNumber1.getReal(), complexConjugateNumber.getReal(), cNumber1.getImaginary(), complexConjugateNumber.getImaginary(), denominator});
            service.getComplexNumber().setReal((cNumber1.getReal() * complexConjugateNumber.getReal()
                                - cNumber1.getImaginary() * complexConjugateNumber.getImaginary())
                                / denominator);
            logger.log(Level.INFO, "Вычисляем мнимую часть комплексного числа путем: (({0} * {1} + {2} * {3})) / {4}", new Object[]{cNumber1.getReal(), complexConjugateNumber.getReal(), cNumber1.getImaginary(), complexConjugateNumber.getReal(), denominator});
            service.getComplexNumber().setImaginary((cNumber1.getReal() * complexConjugateNumber.getImaginary()
                                + cNumber1.getImaginary() * complexConjugateNumber.getReal())
                                / denominator);

            Instant endInstant = Instant.now();
            String endTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneId.systemDefault())
                    .format(endInstant);

            long duration = endInstant.toEpochMilli() - startInstant.toEpochMilli();

            logger.log(Level.INFO, "Окончание операции деления: Время: {0}", endTime);
            logger.log(Level.INFO, "Затраченное время: {0} ms", duration);
            return service.getComplexNumber();

        }else{

            Instant startInstant = Instant.now();
            String startTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneId.systemDefault())
                    .format(startInstant);

            logger.log(Level.INFO, "Начало выполнения деления, когда у второго числа нет мнимой части {0}.", startTime);
            logger.log(Level.INFO, "Вычисляем действительную часть комплексного числа, путем деления {0} / {1}", new Object[]{cNumber1.getReal(), cNumber2.getReal()});
            service.getComplexNumber().setReal(cNumber1.getReal() / cNumber2.getReal());
            logger.log(Level.INFO, "Вычисляем мнимую часть комплексного числа путем деления {0} / {1}", new Object[]{cNumber1.getImaginary(), cNumber2.getReal()});
            service.getComplexNumber().setImaginary(cNumber1.getImaginary() / cNumber2.getReal());

            logger.log(Level.INFO, "В резльтате получаем комплексное число {0}", service.getComplexNumber());
            Instant endInstant = Instant.now();
            String endTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneId.systemDefault())
                    .format(endInstant);

            long duration = endInstant.toEpochMilli() - startInstant.toEpochMilli();

            logger.log(Level.INFO, "Окончание операции деления: Время: {0}", endTime);
            logger.log(Level.INFO, "Затраченное время: {0} ms", duration);
            return service.getComplexNumber();
        }
    }

    @Override
    public ComplexNumber multiply(ComplexNumber cNumber1, ComplexNumber cNumber2) {

        if(cNumber1.getImaginary() != 0 && cNumber2.getImaginary() != 0){
            Instant startInstant = Instant.now();
            String startTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneId.systemDefault())
                    .format(startInstant);
            logger.log(Level.INFO, "Начало операции умножения комплексных чисел, когда оба числа имеют мнимую часть {0}", startTime);
            logger.log(Level.INFO, "Вычисляем первую действительную часть путем умножения {0} * {1}", new Object[]{cNumber1.getReal(), cNumber2.getReal()});
            double real1 = cNumber1.getReal() * cNumber2.getReal();
            logger.log(Level.INFO, "Вычисляем первую мнимую часть путем умножения {0} * {1}", new Object[]{cNumber1.getImaginary(), cNumber2.getImaginary()});
            double imaginary1 = cNumber1.getReal() * cNumber2.getImaginary();
            logger.log(Level.INFO, "Вычисляем вторую мнимую часть путем умножения {0} * {1}", new Object[]{cNumber1.getImaginary(), cNumber2.getReal()});
            double imaginary2 = cNumber1.getImaginary() * cNumber2.getReal();
            logger.log(Level.INFO, "Вычисляем вторую действительную часть путем умножения ({0} * {1}) и умножаем все это на -1, потому что i^2 = -1", new Object[]{cNumber1.getImaginary(), cNumber2.getImaginary()});
            double real2 = (cNumber1.getImaginary() * cNumber2.getImaginary()) * -1D;
            logger.log(Level.INFO, "Вычисляем действительную часть комплексного числа путем сложения двух действительных частей {0} + {1}", new Object[]{real1, real2});
            service.getComplexNumber().setReal(real1 + real2);
            logger.log(Level.INFO, "Вычисляем мнимую часть комплексного числа путем сложения двух мнимых частей {0} + {1}", new Object[]{imaginary1, imaginary2});
            service.getComplexNumber().setImaginary(imaginary1 + imaginary2);
            logger.log(Level.INFO, "Получаем результат умножения двух комплексных чисел {0}", service.getComplexNumber());
            Instant endInstant = Instant.now();
            String endTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneId.systemDefault())
                    .format(endInstant);

            long duration = endInstant.toEpochMilli() - startInstant.toEpochMilli();

            logger.log(Level.INFO, "Окончание операции умножения: Время: {0}", endTime);
            logger.log(Level.INFO, "Затраченное время: {0} ms", duration);
            return service.getComplexNumber();

        }else if (cNumber1.getImaginary() == 0){

            Instant startInstant = Instant.now();
            String startTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneId.systemDefault())
                    .format(startInstant);
            logger.log(Level.INFO, "Начало операции умножения комплексных чисел, когда первое число не имеет мнимую часть {0}", startTime);
            logger.log(Level.INFO, "Вычисляем действительную часть путем умножения {0} * {1}", new Object[]{cNumber1.getReal(), cNumber2.getReal()});
            service.getComplexNumber().setReal(cNumber1.getReal() * cNumber2.getReal());
            logger.log(Level.INFO, "Вычисляем мнимую часть путем умножения {0} * {1}", new Object[]{cNumber1.getReal(), cNumber2.getImaginary()});
            service.getComplexNumber().setImaginary(cNumber1.getReal() * cNumber2.getImaginary());
            logger.log(Level.INFO, "В результат получаем комплексное число {0}", service.getComplexNumber());
            Instant endInstant = Instant.now();
            String endTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneId.systemDefault())
                    .format(endInstant);

            long duration = endInstant.toEpochMilli() - startInstant.toEpochMilli();

            logger.log(Level.INFO, "Окончание операции умножения: Время: {0}", endTime);
            logger.log(Level.INFO, "Затраченное время: {0} ms", duration);
            return service.getComplexNumber();

        }else{

            Instant startInstant = Instant.now();
            String startTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneId.systemDefault())
                    .format(startInstant);
            logger.log(Level.INFO, "Начало операции умножения комплексных чисел, когда второе число не имеет мнимую часть {0}", startTime);
            logger.log(Level.INFO, "Вычисляем действительную часть путем умножения {0} * {1}", new Object[]{cNumber1.getReal(), cNumber2.getReal()});
            service.getComplexNumber().setReal(cNumber1.getReal() * cNumber2.getReal());
            logger.log(Level.INFO, "Вычисляем мнимую часть путем умножения {0} * {1}", new Object[]{cNumber1.getImaginary(), cNumber2.getReal()});
            service.getComplexNumber().setImaginary(cNumber1.getImaginary() * cNumber2.getReal());
            logger.log(Level.INFO, "В результате получаем число {0}", service.getComplexNumber());
            Instant endInstant = Instant.now();
            String endTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneId.systemDefault())
                    .format(endInstant);

            long duration = endInstant.toEpochMilli() - startInstant.toEpochMilli();

            logger.log(Level.INFO, "Окончание операции умножения: Время: {0}", endTime);
            logger.log(Level.INFO, "Затраченное время: {0} ms", duration);
            return service.getComplexNumber();
        }
        
    }
}
