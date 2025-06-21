package pcbuilder.service;

import pcbuilder.components.Componente;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


public class ComponenteService<T extends Componente> {
    protected final List<T> storage = new ArrayList<>();

    public List<T> buscarTodos() {
        return new ArrayList<>(storage);
    }

    public List<T> buscarPorNome(String termo) {
        if (termo == null || termo.isBlank()) {
            return buscarTodos();
        }
        String low = termo.toLowerCase(Locale.ROOT);
        return storage.stream()
            .filter(c -> c.getNome().toLowerCase(Locale.ROOT).contains(low))
            .collect(Collectors.toList());
    }

    public void salvarOuAtualizar(T comp) {
        if (comp == null) return;
        storage.removeIf(old -> old.getNome().equals(comp.getNome()));
        storage.add(comp);
    }

    public void excluir(String nome) {
        storage.removeIf(c -> c.getNome().equals(nome));
    }
}
