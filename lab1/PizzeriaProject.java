import java.util.ArrayList;
import java.util.List;

public class PizzeriaProject {

    // Singleton Implementation
    public static class Pizzeria {
        private static Pizzeria instance;
        private String name;

        private Pizzeria(String name) {
            this.name = name;
        }

        public static Pizzeria getInstance(String name) {
            if (instance == null) {
                instance = new Pizzeria(name);
            }
            return instance;
        }

        public String getName() {
            return name;
        }
    }

    public static class SingletonDemo {
        public static void main(String[] args) {
            Pizzeria pizzeria1 = Pizzeria.getInstance("Pizza Palace");
            Pizzeria pizzeria2 = Pizzeria.getInstance("Pizza Kingdom");

            System.out.println(pizzeria1.getName()); // Output: Pizza Palace
            System.out.println(pizzeria2.getName()); // Output: Pizza Palace
            System.out.println(pizzeria1 == pizzeria2); // Output: true
        }
    }

    // Factory Method Implementation
    public static abstract class Pizza {
        public abstract String prepare();
    }

    public static class Margherita extends Pizza {
        @Override
        public String prepare() {
            return "Preparing Margherita Pizza";
        }
    }

    public static class Pepperoni extends Pizza {
        @Override
        public String prepare() {
            return "Preparing Pepperoni Pizza";
        }
    }

    public static abstract class PizzaFactory {
        public abstract Pizza createPizza();
    }

    public static class MargheritaFactory extends PizzaFactory {
        @Override
        public Pizza createPizza() {
            return new Margherita();
        }
    }

    public static class PepperoniFactory extends PizzaFactory {
        @Override
        public Pizza createPizza() {
            return new Pepperoni();
        }
    }

    public static class FactoryMethodDemo {
        public static void main(String[] args) {
            PizzaFactory margheritaFactory = new MargheritaFactory();
            Pizza margherita = margheritaFactory.createPizza();
            System.out.println(margherita.prepare()); // Output: Preparing Margherita Pizza

            PizzaFactory pepperoniFactory = new PepperoniFactory();
            Pizza pepperoni = pepperoniFactory.createPizza();
            System.out.println(pepperoni.prepare()); // Output: Preparing Pepperoni Pizza
        }
    }

    // Builder Implementation
    public static abstract class PizzaBuilder {
        protected PizzaProduct product = new PizzaProduct();

        public abstract void addDough();
        public abstract void addSauce();
        public abstract void addToppings();

        public PizzaProduct getProduct() {
            PizzaProduct result = product;
            product = new PizzaProduct();
            return result;
        }
    }

    public static class HawaiianPizzaBuilder extends PizzaBuilder {
        @Override
        public void addDough() {
            product.add("Dough: Thin Crust");
        }

        @Override
        public void addSauce() {
            product.add("Sauce: Tomato");
        }

        @Override
        public void addToppings() {
            product.add("Toppings: Ham, Pineapple");
        }
    }

    public static class PizzaProduct {
        private List<String> parts = new ArrayList<>();

        public void add(String part) {
            parts.add(part);
        }

        public void listParts() {
            System.out.println("Pizza parts: " + String.join(", ", parts));
        }
    }

    public static class Director {
        private PizzaBuilder builder;

        public void setBuilder(PizzaBuilder builder) {
            this.builder = builder;
        }

        public void buildHawaiianPizza() {
            builder.addDough();
            builder.addSauce();
            builder.addToppings();
        }
    }

    public static class BuilderDemo {
        public static void main(String[] args) {
            Director director = new Director();
            PizzaBuilder builder = new HawaiianPizzaBuilder();
            director.setBuilder(builder);

            System.out.println("Hawaiian Pizza: ");
            director.buildHawaiianPizza();
            builder.getProduct().listParts();
        }
    }

    // Main method to run all demos
    public static void main(String[] args) {
        System.out.println("Singleton Demo:");
        SingletonDemo.main(args);

        System.out.println("\nFactory Method Demo:");
        FactoryMethodDemo.main(args);

        System.out.println("\nBuilder Demo:");
        BuilderDemo.main(args);
    }
}