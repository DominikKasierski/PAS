package com.mycompany.firstapplication.Users;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class UserDTOWrapper {

    private static UserDTO userWrapper(User user) {
        return new UserDTO(user.getUuid(), user.getLogin(), user.getName(), user.getSurname());
    }

    private static UserDTO userWrapper(Client user) {
        return new UserDTO(user.getUuid(), user.getLogin(), user.getName(), user.getSurname(),
                user.getNumberOfChildren(),
                user.getAgeOfTheYoungestChild());
    }

    public static UserDTO wrap(User user) {
        if (user instanceof Client) {
            return userWrapper(createClientFromUser(user));
        } else {
            return userWrapper(user);
        }
    }

    public static List<UserDTO> listWrapper(List<User> userList) {
        List<UserDTO> wrappedList = new ArrayList<>();
        for (User user : userList) {
            if (user instanceof Client) {
                wrappedList.add(wrap(createClientFromUser(user)));
            } else {
                wrappedList.add(wrap(user));
            }
        }
        return wrappedList;
    }

    private static Client createClientFromUser(User user) {
        Client client = new Client();
        try {
            BeanUtils.copyProperties(client, user);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return client;
    }
}