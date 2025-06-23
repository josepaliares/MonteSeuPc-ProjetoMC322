package pcbuilder.service;

import pcbuilder.components.PlacaMae;
import pcbuilder.factory.ComponenteFactory;
import pcbuilder.components.Marca;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PlacaMaeService extends ComponenteService<PlacaMae> {
    private static final String ARQUIVO = "data/placas_mae.json";

    public PlacaMaeService() {
        carregarDeJson();
        if (storage.isEmpty()) {
            storage.add(
                ComponenteFactory.criarPlacaMae(
                    "Asus Prime Z490",
                    75000,
                    "Placa-mãe ATX LGA1200, ideal para 10ª geração Intel",
                    Marca.INTEL,
                    "/images/prime_z490.png",
                    "LGA1200",
                    "Z490",
                    4,
                    16,        
                    "DDR4",    
                    120    
                )
            );
            storage.add(
                ComponenteFactory.criarPlacaMae(
                    "MSI B550 Tomahawk",
                    65000,
                    "Placa-mãe ATX AM4, suporte a Ryzen 5000",
                    Marca.AMD,
                    "/images/b550_tomahawk.png",
                    "AM4",
                    "B550",
                    4,32,        
                    "DDR5",    
                    240    
                )
            );
            storage.add(
                ComponenteFactory.criarPlacaMae(
                    "Gigabyte Z690 Aorus Elite",
                    90000,
                    "Placa-mãe ATX LGA1700, pronta para Intel 12ª geração",
                    Marca.INTEL,
                    "/images/z690_aorus.png",
                    "LGA1700",
                    "Z690",
                    4,
                    16,        
                    "DDR4",    
                    120      
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
                Type listType = new TypeToken<List<PlacaMae>>() {}.getType();
                List<PlacaMae> placas = new Gson().fromJson(json, listType);
                storage.clear();
                storage.addAll(placas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void salvarOuAtualizar(PlacaMae comp) {
        super.salvarOuAtualizar(comp);
        salvarEmJson();
    }

    @Override
    public void excluir(String nome) {
        super.excluir(nome);
        salvarEmJson();
    }
}
