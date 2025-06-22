package pcbuilder.service;

import pcbuilder.components.Fonte;
import pcbuilder.factory.ComponenteFactory;
import pcbuilder.components.Marca;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FonteService extends ComponenteService<Fonte> {
    private static final String ARQUIVO = "fontes.json";

    public FonteService() {
        carregarDeJson();
        if (storage.isEmpty()) {
            storage.add(ComponenteFactory.criarFonte(
                "Corsair CX550",
                30000,
                "Fonte semimodular 550W 80 Plus Bronze",
                Marca.AMD,
                "/images/fonte_cx550.png",
                550
            ));
            storage.add(ComponenteFactory.criarFonte(
                "EVGA SuperNOVA 750 G5",
                60000,
                "Fonte fully-modular 750W 80 Plus Gold",
                Marca.INTEL,
                "/images/evga_750g5.png",
                750
            ));
            storage.add(ComponenteFactory.criarFonte(
                "Seasonic Focus GX-650",
                55000,
                "Fonte fully-modular 650W 80 Plus Gold",
                Marca.NVIDIA,
                "/images/seasonic_gx650.png",
                650
            ));
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
                Type listType = new TypeToken<List<Fonte>>(){}.getType();
                List<Fonte> fontes = new Gson().fromJson(json, listType);
                storage.clear();
                storage.addAll(fontes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void salvarOuAtualizar(Fonte comp) {
        super.salvarOuAtualizar(comp);
        salvarEmJson();
    }

    @Override
    public void excluir(String nome) {
        super.excluir(nome);
        salvarEmJson();
    }
}
