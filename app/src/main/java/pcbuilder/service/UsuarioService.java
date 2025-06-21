package pcbuilder.service;

import pcbuilder.usuario.Usuario;
import pcbuilder.usuario.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

public class UsuarioService {

    private static final UsuarioService INSTANCE = new UsuarioService();

    private final List<Usuario> storage = new ArrayList<>();

    private UsuarioService() {
        storage.add(new Usuario("admin", "admin@pc", "1234", Role.ADMIN));
        storage.add(new Usuario("cliente", "client@pc", "1234", Role.CLIENT));
    }

    public static UsuarioService getInstance() {
        return INSTANCE;
    }

    public Optional<Usuario> login(String email, String senha) {
        return storage.stream()
            .filter(u -> u.getEmail().equals(email) && u.getSenha().equals(senha))
            .findFirst();
    }

    public List<Usuario> buscarTodos() {
        return new ArrayList<>(storage);
    }

    public List<Usuario> buscarPorNomeOuEmail(String termo) {
        if (termo == null || termo.isBlank()) {
            return buscarTodos();
        }
        String low = termo.toLowerCase(Locale.ROOT);
        return storage.stream()
            .filter(u ->
                u.getUsername().toLowerCase(Locale.ROOT).contains(low) ||
                u.getEmail().toLowerCase(Locale.ROOT).contains(low)
            )
            .collect(Collectors.toList());
    }

    public void salvarOuAtualizar(Usuario u) {
        if (u == null) return;
        storage.removeIf(old -> old.getUsername().equals(u.getUsername()));
        storage.add(u);
    }

    public void excluir(String username) {
        storage.removeIf(u -> u.getUsername().equals(username));
    }
}
