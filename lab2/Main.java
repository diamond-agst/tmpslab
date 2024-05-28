// Адаптер (Adapter)
interface OrderInterface {
    String orderPizza();
}

class PizzaMaker {
    String makePizza() {
        return "";
    }
}

class PizzaChef extends PizzaMaker {
    @Override
    String makePizza() {
        return "Pizza is ready!";
    }
}

class PizzaAdapter implements OrderInterface {
    private final PizzaMaker pizzaMaker;

    public PizzaAdapter(PizzaMaker pizzaMaker) {
        this.pizzaMaker = pizzaMaker;
    }

    @Override
    public String orderPizza() {
        return pizzaMaker.makePizza();
    }
}

// Декоратор (Decorator)
interface Pizza {
    String make();
}

class ConcretePizza implements Pizza {
    @Override
    public String make() {
        return "Plain Pizza";
    }
}

abstract class Decorator implements Pizza {
    protected final Pizza pizza;

    public Decorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String make() {
        return pizza.make();
    }
}

class CheeseDecorator extends Decorator {
    public CheeseDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String make() {
        return pizza.make() + " with cheese";
    }
}

class MushroomDecorator extends Decorator {
    public MushroomDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String make() {
        return pizza.make() + " with mushrooms";
    }
}

// Фасад (Facade)
class DoughPreparer {
    String prepareDough() {
        return "Dough is ready!";
    }
}

class ToppingAdder {
    String addToppings() {
        return "Toppings are added!";
    }
}

class PizzaFacade {
    private final DoughPreparer doughPreparer;
    private final ToppingAdder toppingAdder;

    public PizzaFacade() {
        this.doughPreparer = new DoughPreparer();
        this.toppingAdder = new ToppingAdder();
    }

    String makePizza() {
        String dough = doughPreparer.prepareDough();
        String toppings = toppingAdder.addToppings();
        return dough + " " + toppings;
    }
}

public class Main {
    public static void main(String[] args) {
        // Адаптер
        PizzaMaker chef = new PizzaChef();
        OrderInterface adapter = new PizzaAdapter(chef);
        String result = adapter.orderPizza();
        System.out.println("Adapter Example: " + result);

        // Декоратор
        Pizza pizza = new ConcretePizza();
        pizza = new CheeseDecorator(pizza);
        pizza = new MushroomDecorator(pizza);
        result = pizza.make();
        System.out.println("Decorator Example: " + result);

        // Фасад
        PizzaFacade facade = new PizzaFacade();
        result = facade.makePizza();
        System.out.println("Facade Example: " + result);
    }
}