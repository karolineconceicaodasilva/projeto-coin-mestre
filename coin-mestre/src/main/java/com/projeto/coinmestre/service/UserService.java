package com.projeto.coinmestre.service;

import com.projeto.coinmestre.base.PageReq;
import com.projeto.coinmestre.base.PageRes;
import com.projeto.coinmestre.config.security.AuthUtil;
import com.projeto.coinmestre.dto.req.UserReqDTO;
import com.projeto.coinmestre.dto.res.UserResDTO;
import com.projeto.coinmestre.model.User;
import com.projeto.coinmestre.repository.UserRepository;
import com.projeto.coinmestre.util.SearchUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository repository;
    private final TokenStore tokenStore;
    private final PasswordEncoder passwordEncoder;

    public PageRes<UserResDTO> findAll(PageReq query) {

        Specification<User> deleted = SearchUtils.specByDeleted(query.isDeleted());
        Specification<User> filters = SearchUtils.specByFilter(query.getFilter(), "id",
                "username", "email", "name");

        Page<User> page = this.repository.findAll(deleted.and(filters), query.toPageRequest());

        return new PageRes<>(page.getContent().stream().map(UserResDTO::new).collect(Collectors.toList()),
                page.getTotalElements(), page.getTotalPages());
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
            user.setPassword(this.passwordEncoder.encode(dto.getPassword()));
            this.repository.save(user);
        } else {
            throw new RuntimeException("Senha e Confirmação de senha não são iguais!");
        }
        return new UserResDTO(user);
    }

    public UserResDTO update(Long id, UserReqDTO dto) {
        Optional<User> optionalUser = this.repository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(dto.getName());
            user.setEmail(dto.getEmail().trim().toLowerCase());
            user.setPassword(this.passwordEncoder.encode(dto.getPassword()));
            user.setUsername(dto.getEmail().trim().toLowerCase());

            this.repository.save(user);

            return new UserResDTO(user);

        } else {
            throw new RuntimeException("Usuário não encontrada na base de dados.");
        }
    }

    public void logicalExclusion(Long id) {

        if (this.repository.findByIdAndNotDeleted(id).isEmpty())
            throw new RuntimeException("Usuário não encontrada na base de dados.");

        this.repository.softDelete(id);
    }

    public void restoreDeleted(Long id) {

        if (this.repository.findDeletedById(id).isEmpty())
            throw new RuntimeException("Usuário não encontrada na base de dados.");

        this.repository.restoreDeleted(id);
    }

    public UserResDTO findByLoggedUser() {
        Long idUser = AuthUtil.getUserId();
        assert idUser != null;
        Optional<User> optionalUser = this.repository.findById(idUser);

        return optionalUser.map(UserResDTO::new).orElse(null);
    }

    public User findByLoggedUserEntity() {
        Long idUser = AuthUtil.getUserId();
        assert idUser != null;
        Optional<User> optionalUser = this.repository.findById(idUser);

        return optionalUser.orElse(null);
    }

    public void logout() {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof OAuth2Authentication) {
            OAuth2AccessToken accessToken = tokenStore
                    .getAccessToken((OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication());
            if (accessToken != null && accessToken.getRefreshToken() != null) {
                this.tokenStore.removeAccessTokenUsingRefreshToken(accessToken.getRefreshToken());
                this.tokenStore.removeRefreshToken(accessToken.getRefreshToken());
            }
        }
    }

}
