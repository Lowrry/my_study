package simple;

/**
 * User: luxiaochun<p/>
 * Date: 2017/12/25<p/>
 * Time: 10:50<p/>
 */
public class NewTest {

    public static void main(String[] args) {
        final int NUM = 100000;

        for(int j=0;j<20;j++){

            PriceAccess p = new PriceAccess() {
                @Override
                public Long getPrice() {
                    return 10L;
                }
            };

            long start1 = System.currentTimeMillis();
            for(int i=0;i<NUM;i++){
                p = new CoffeePriceAccess(p, 10L);
            }
            System.out.println("total value = " + p.getPrice());
            System.out.println("use time = " + (System.currentTimeMillis() - start1));

            long start2 = System.currentTimeMillis();
            Long total = 10L;
            for(int i=0;i<NUM;i++){
                total += 10L;
            }
            System.out.println("total value = " + total);
            System.out.println("use time = " + (System.currentTimeMillis() - start2));

            System.out.println("===========================");

        }


    }



    private static class Price{
        Long value;
    }

    interface PriceAccess{
        Long getPrice();
    }

    private static class CoffeePriceAccess implements PriceAccess{

        PriceAccess priceAccess;

        Long addValue;

        public CoffeePriceAccess(PriceAccess priceAccess, Long addValue) {
            this.priceAccess = priceAccess;
            this.addValue = addValue;
        }

        @Override
        public Long getPrice() {
            return this.priceAccess.getPrice() + addValue;
        }
    }

}
