package pcbuilder.service;

import pcbuilder.components.Cooler;
import pcbuilder.factory.ComponenteFactory;
import pcbuilder.components.Marca;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CoolerService extends ComponenteService<Cooler> {
    private static final String ARQUIVO = "coolers.json";

    public CoolerService() {
        carregarDeJson();
        if (storage.isEmpty()) {
            storage.add(
                ComponenteFactory.criarCooler(
                    "Cooler Master Hyper 212",
                    250,
                    "Cooler a ar com quatro heatpipes",
                    Marca.AMD,
                    "/images/cooler_hyper212.png",
                    "Ventilador"
                )
            );
            storage.add(
                ComponenteFactory.criarCooler(
                    "Corsair H100i RGB Platinum",
                    85000,
                    "Watercooler líquido 240mm com iluminação RGB",
                    Marca.NVIDIA,
                    "/images/corsair_h100i.png",
                    "Watercooler"
                )
            );
            storage.add(
                ComponenteFactory.criarCooler(
                    "Noctua NH-D15",
                    65000,
                    "Cooler a ar premium de alta performance",
                    Marca.INTEL,
                    "/images/noctua_nhd15.png",
                    "Ventilador"
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
                Type listType = new TypeToken<List<Cooler>>() {}.getType();
                List<Cooler> coolers = new Gson().fromJson(json, listType);
                storage.clear();
                storage.addAll(coolers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void salvarOuAtualizar(Cooler comp) {
        super.salvarOuAtualizar(comp);
        salvarEmJson();
    }

    @Override
    public void excluir(String nome) {
        super.excluir(nome);
        salvarEmJson();
    }
}
