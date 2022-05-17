package pro.manage.entity.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import pro.manage.entity.Address;
import pro.manage.entity.Audit;
import pro.manage.entity.Property;
import pro.manage.entity.User;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LandlordDto extends Audit {




}
