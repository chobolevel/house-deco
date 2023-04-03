package kr.co.deco.house.entity.goods;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Goods {

    private String id;
    private String title;
    private String description;
    private String thumbNail;
    private String createDate;
    private String updateDate;

}
