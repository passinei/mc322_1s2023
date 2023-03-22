public class Main {

    public static void main(String[] args) {
        // Implementação de clientes
        Cliente[] clientes = {
            new Cliente("Icedois", "111.444.777-00", "02/02/2002", 21, "Av. Albert Einstein, 1251"),
            new Cliente("Icetrês", "111.444.777-35", "03/03/2003", 20, "Av. Saturnino de Brito, 573"),
        };
        for (Cliente cliente : clientes) {
            System.out.println(cliente.toString());
        }
        
        // Implementação de sinistros
        Sinistro[] sinistros = {
            new Sinistro("01/01/2021", "Rua Pitágoras, 353"),
            new Sinistro("02/02/2022", "Av. Albert Einstein, 1251"),
            new Sinistro("03/03/2023", "Av. Saturnino de Brito, 573"),
        };
        for (Sinistro sinistro : sinistros) {
            System.out.println("SINISTRO");
            System.out.println("ID: " + sinistro.getId());
            System.out.println("Data: " + sinistro.getData());
            System.out.println("Endereço: " + sinistro.getEndereco() + "\n");
        }
        
        // Implementação de seguradora
        Seguradora seguradora = new Seguradora(
            "Seguradora Seguros",
            "19912345678",
            "suporte@seguros.com",
            "Rua Tessália Vieira de Camargo, 126"
        );
        seguradora.setEmail("contato@seguros.com");
        
        System.out.println("SEGURADORA");
        System.out.println("Nome: " + seguradora.getNome());
        System.out.println("Telefone: " + seguradora.getTelefone());
        System.out.println("Email: " + seguradora.getEmail());
        System.out.println("Endereço: " + seguradora.getEndereco() + "\n");
        
        // Implementação de veículo
        Veiculo veiculo = new Veiculo("AAA-0000", "Marca genérica", "Modelo genérico");
        System.out.println("VEÍCULO");
        System.out.println("Placa antes: " + veiculo.getPlaca());
        veiculo.setPlaca("AAA-0A00");
        System.out.println("Placa atual: " + veiculo.getPlaca() + "\n");
    }

}
