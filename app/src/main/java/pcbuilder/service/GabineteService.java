package pcbuilder.service;

import pcbuilder.components.Gabinete;
import pcbuilder.factory.ComponenteFactory;
import pcbuilder.components.Marca;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GabineteService extends ComponenteService<Gabinete> {
    private static final String ARQUIVO = "gabinetes.json";

    public GabineteService() {
        carregarDeJson(); 
        if (storage.isEmpty()) {
            storage.add(
                ComponenteFactory.criarGabinete(
                    "Cooler Master NR600",
                    40000,
                    "Gabinete Mid Tower com boa ventilação frontal",
                    Marca.AMD,
                    "/images/nr600.png",
                    450,   
                    210,   
                    470    
                )
            );
            storage.add(
                ComponenteFactory.criarGabinete(
                    "Corsair 2000D Airflow",
                    55000,
                    "Gabinete Full Tower com painel frontal perfurado",
                    Marca.INTEL,
                    "/images/2000d_airflow.png",
                    52,
                    24,
                    55
                )
            );
            storage.add(
                ComponenteFactory.criarGabinete(
                    "NZXT H510",
                    30000,
                    "Gabinete Mid Tower com design minimalista e laterais em vidro temperado",
                    Marca.NVIDIA,
                    "/images/nzxt_h510.png",
                    46,
                    21,
                    42
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
                Type listType = new TypeToken<List<Gabinete>>(){}.getType();
                List<Gabinete> gabinetes = new Gson().fromJson(json, listType);
                storage.clear();
                storage.addAll(gabinetes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void salvarOuAtualizar(Gabinete comp) {
        super.salvarOuAtualizar(comp);
        salvarEmJson();
    }

    @Override
    public void excluir(String nome) {
        super.excluir(nome);
        salvarEmJson();
    }
}
