package Items.Shop.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Table (name = "Items")
@Data
@Builder
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String Name;

    private int Count;

    private String Description;

    @Builder
    public Item(int id, String name, int count, String description) {
        this.Id = id;
        this.Name = name;
        this.Count = count;
        this.Description = description;
    }

    public Item() {

    }
}
