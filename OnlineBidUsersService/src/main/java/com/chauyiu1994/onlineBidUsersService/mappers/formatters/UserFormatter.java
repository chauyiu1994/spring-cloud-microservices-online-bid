package com.chauyiu1994.onlineBidUsersService.mappers.formatters;

import com.chauyiu1994.onlineBidUsersService.shared.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserFormatter {

    public static List<Role> toRoleList(List<String> roleStrList) {

        return roleStrList == null
                ? new ArrayList<>()
                : roleStrList.stream()
                        .map(roleStr -> Role.valueOf(roleStr.trim()))
                        .distinct()
                        .collect(Collectors.toList());
    }
}
