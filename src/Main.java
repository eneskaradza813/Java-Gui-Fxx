
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.util.Callback;


public class Main {

    public static void main(String[] args) {
        IntegerProperty p1 = new SimpleIntegerProperty(10);
        IntegerProperty p2 = new SimpleIntegerProperty(20);
        
        Callback<IntegerProperty, Observable[]> extractor = (IntegerProperty p)->{
            return new Observable[]{p};
        };

        ObservableList<IntegerProperty> list = FXCollections.observableArrayList(extractor);
        list.addAll(p1, p2);
        
        list.addListener(new ListChangeListener<IntegerProperty>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends IntegerProperty> change) {
                 while(change.next()){
                     if(change.wasPermutated()){
                         System.out.println("Permutated: " + change.getFrom() + " to " + change.getTo());
                     }else if(change.wasUpdated()){
                         System.out.println("Updatet from: " + change.getFrom() + " to " + change.getTo());
                     }else if(change.wasReplaced()){
                         System.out.println("Replaced from: " + change.getFrom() + "to " + change.getTo());
                     }else {
                         if(change.wasRemoved()){
                             System.out.println("Removing from " + change.getFrom() + "to " + change.getTo());
                         }else if(change.wasAdded()){
                             System.out.println("Added from " + change.getFrom() + "to" + change.getTo());
                         }
                     }
                 }
            }
        });
        p2.set(30);
        
        
        /*FXCollections.sort(list);
        System.out.println("After sorting: " + list);
        
        list.set(0, "6");
        System.out.println("After replacing:  " + list);
        
        list.removeAll("2", "5");
        System.out.println("After removing: " + list);
        
        list.add("22");
        System.out.println("After adding: " + list);*/
    }

}
