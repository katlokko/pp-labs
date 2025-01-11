package org.example;
// один объект содержит ссылку на другой и делегирует ему работу, вместо того чтобы самому наследовать его поведение
// Интерфейс компонента
interface Pizza {
    String getDescription();
    double cost();
}

// Конкретный компонент - класс оборачиваемых объектов
class MargheritaPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Пицца Маргарита";
    }

    @Override
    public double cost() {
        return 8.00; // базовая цена
    }
}

// Декоратор - декоратор передает все свои операции вложенному объекту (pizza)
//абстрактный класс и служит базовым классом для всех декораторов
abstract class PizzaDecorator implements Pizza {
    protected Pizza pizza;

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }

    @Override
    public double cost() {
        return pizza.cost();
    }
}

// Конкретный декоратор для сыра
class CheeseDecorator extends PizzaDecorator {
    public CheeseDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", с дополнительным сыром";
    }

    @Override
    public double cost() {
        return pizza.cost() + 1.00; // добавляем стоимость сыра
    }
}

// Конкретный декоратор для пепперони
class PepperoniDecorator extends PizzaDecorator {
    public PepperoniDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + " и пепперони";
    }

    @Override
    public double cost() {
        return pizza.cost() + 1.50; // добавляем стоимость оливок
    }
}

// Основной класс
public class PizzaShop {
    public static void main(String[] args) {
        Pizza myPizza = new MargheritaPizza();
        System.out.println(myPizza.getDescription() + " стоит: $" + myPizza.cost());

        // Добавляем сыр
        myPizza = new CheeseDecorator(myPizza);
        System.out.println(myPizza.getDescription() + " стоит: $" + myPizza.cost());

        // Добавляем пепперони
        myPizza = new PepperoniDecorator(myPizza);
        System.out.println(myPizza.getDescription() + " стоит: $" + myPizza.cost());
    }
}