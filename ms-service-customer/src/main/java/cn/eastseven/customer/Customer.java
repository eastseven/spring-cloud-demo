package cn.eastseven.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by dongqi on 17/3/20.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Entity
@Table(name = "t_customer")
public class Customer implements Serializable {

    public static final int STATUS_NORMAL = 1;
    public static final int STATUS_OFF = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();

    private int status = STATUS_NORMAL;

    private String ip;
}
