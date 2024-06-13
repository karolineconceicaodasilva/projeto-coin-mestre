package com.projeto.coinmestre.service;

import com.projeto.coinmestre.dto.req.ExpenseReqDTO;
import com.projeto.coinmestre.dto.req.UserReqDTO;
import com.projeto.coinmestre.dto.res.ExpenseResDTO;
import com.projeto.coinmestre.dto.res.UserResDTO;
import com.projeto.coinmestre.model.Expense;
import com.projeto.coinmestre.model.User;
import com.projeto.coinmestre.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {
    private UserRepository repository;


    public List<UserResDTO> findAllUsers() {

        List<User> userList = this.repository.findAll();

        List<UserResDTO> resDTOList = new ArrayList<>();

        for (User user : userList) {
            UserResDTO userResDTO = new UserResDTO(user);
            resDTOList.add(userResDTO);
        }
        return resDTOList;
    }

    public UserResDTO findById(Long id) {
        Optional<User> optionalUser = this.repository.findById(id);
        if (optionalUser.isPresent()) {
            return new UserResDTO(optionalUser.get());
        } else {
            throw new RuntimeException("Usuário não encontrada na base de dados");
        }
    }

    public UserResDTO insert(UserReqDTO dto) {
        User user;
        if (dto.getPassword().equals(dto.getConfirmationPassword())) {
            user = UserReqDTO.dtoToModel(dto);
            this.repository.save(user);
        } else {
            throw new RuntimeException("Senha e Confirmação de senha não são iguais!");
        }
        return new UserResDTO(user);
    }

    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }

    public UserResDTO update(Long id, UserReqDTO dto) {
        Optional<User> optionalUser = this.repository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(dto.getName());
            user.setEmail(dto.getEmail());
            user.setPassword(dto.getPassword());

            this.repository.save(user);

            return new UserResDTO(user);

        } else {

            throw new RuntimeException("Usuário não encontrada na base de dados.");
        }
    }


}
