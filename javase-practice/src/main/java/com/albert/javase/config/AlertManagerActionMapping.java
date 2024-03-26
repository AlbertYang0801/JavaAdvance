package com.albert.javase.config;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlertManagerActionMapping implements Serializable {
    List<String> alias;
    List<AlertManagerAction> methods;
}
