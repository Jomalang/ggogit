package Recorders.ggogit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Branch {
    private String id;
    private String src;
    private String regDate;
    private String name;
    private int leaf;
    private int view;
}
