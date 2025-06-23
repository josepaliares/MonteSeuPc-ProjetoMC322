package pcbuilder.service;

import pcbuilder.components.Processador;
import pcbuilder.factory.ComponenteFactory;
import pcbuilder.components.Marca;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ProcessadorService extends ComponenteService<Processador> {
    private static final String ARQUIVO = "data/processadores.json";

    public ProcessadorService() {
        carregarDeJson();
        if (storage.isEmpty()) {
            storage.add(
                ComponenteFactory.criarProcessador(
                    "Intel Core i7-10700K",
                    160000,
                    "8 núcleos, 16 threads, até 5.1GHz Turbo",
                    Marca.INTEL,
                    "/images/i7-10700k.png",
                    "Comet Lake",
                    "LGA1200",
                    8,
                    16
                )
            );
            storage.add(
                ComponenteFactory.criarProcessador(
                    "AMD Ryzen 5 5600X",
                    130000,
                    "6 núcleos, 12 threads, até 4.6GHz Boost",
                    Marca.AMD,
                    "/images/ryzen5-5600x.png",
                    "Zen 3",
                    "AM4",
                    6,
                    12
                )
            );
            storage.add(
                ComponenteFactory.criarProcessador(
                    "Intel Core i5-12400F",
                    100000,
                    "6 núcleos, 12 threads, sem gráficos integrados",
                    Marca.INTEL,
                    "/images/i5-12400f.png",
                    "Alder Lake",
                    "LGA1700",
                    6,
                    12
                )
            );
            salvarEmJson();
        }
    }

    public void salvarEmJson() {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(storage);
            Files.write(Paths.get(ARQUIVO), json.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void carregarDeJson() {
        try {
            if (Files.exists(Paths.get(ARQUIVO))) {
                String json = new String(Files.readAllBytes(Paths.get(ARQUIVO)));
                Type listType = new TypeToken<List<Processador>>() {}.getType();
                List<Processador> processadores = new Gson().fromJson(json, listType);
                storage.clear();
                storage.addAll(processadores);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void salvarOuAtualizar(Processador comp) {
        super.salvarOuAtualizar(comp);
        salvarEmJson();
    }

    @Override
    public void excluir(String nome) {
        super.excluir(nome);
        salvarEmJson();
    }
}
