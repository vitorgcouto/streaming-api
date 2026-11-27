# Streaming API — Sistema de Gerenciamento de Contas e Usuários

## Sobre o projeto

Este projeto consiste no desenvolvimento de uma API REST para gerenciamento de contas e usuários de uma plataforma de streaming, simulando funcionalidades e regras de negócio presentes em sistemas reais do mercado.

A aplicação permite gerenciar contas de assinantes, usuários compartilhados, planos de assinatura, perfis com restrições por faixa etária e histórico de filmes assistidos.

O principal objetivo foi desenvolver uma solução backend estruturada, aplicando conceitos de desenvolvimento orientado a objetos, arquitetura em camadas, controle de acesso, persistência de dados e testes automatizados, com foco na integridade e confiabilidade das regras de negócio.

## Funcionalidades

- **Gerenciamento de usuários:** criação de usuários assinantes e não assinantes, com seus respectivos vínculos a uma conta.
- **Controle de acesso:** restrição das operações de gerenciamento de usuários ao assinante responsável pela conta.
- **Compartilhamento de contas:** validação automática do limite de usuários permitido pelo plano contratado.
- **Planos de assinatura:** gerenciamento e migração de planos, aplicando as alterações a todos os usuários vinculados à conta.
- **Controle de conteúdo:** listagem de filmes disponíveis de acordo com o plano e a classificação etária do perfil.
- **Validação de reprodução:** bloqueio de filmes incompatíveis com a faixa etária do usuário.
- **Histórico de reprodução:** registro dos filmes assistidos, preservando a ordem cronológica e permitindo ocorrências repetidas.

## Tecnologias e dependências

O projeto foi desenvolvido utilizando Java e o ecossistema Spring Boot, com as seguintes tecnologias e bibliotecas:

| Tecnologia | Aplicação no projeto |
|---|---|
| **Java** | Linguagem utilizada no desenvolvimento da aplicação. |
| **Spring Boot** | Estrutura principal para construção e configuração do backend. |
| **Spring Web** | Desenvolvimento dos endpoints REST responsáveis pelas funcionalidades da API. |
| **Spring Data JPA** | Mapeamento das entidades e operações de persistência e consulta no banco de dados. |
| **H2 Database** | Banco de dados em memória utilizado durante o desenvolvimento e execução dos testes. |
| **Spring Validation** | Validação dos dados recebidos pelas requisições, garantindo a integridade das entradas. |
| **Spring Security** | Utilização do BCrypt para criptografia de senhas. |
| **Lombok** | Redução de código repetitivo por meio da geração automática de getters, construtores e outros métodos. |

## Regras de negócio

A aplicação implementa regras específicas para garantir o funcionamento consistente da plataforma:

- Cada conta possui um usuário assinante responsável pelo gerenciamento.
- Usuários não assinantes dependem de uma conta existente para serem vinculados.
- O número de usuários compartilhados respeita o limite estabelecido pelo plano.
- Cada usuário possui um perfil: infantil, adolescente ou adulto.
- O acesso aos filmes depende das permissões do plano e da classificação indicativa do perfil.
- A migração de plano atualiza as condições de utilização de todos os usuários da conta.
- O histórico mantém os registros de reprodução sem eliminar filmes assistidos anteriormente.

## Qualidade e testes

O desenvolvimento utiliza a abordagem **TDD (Test-Driven Development)**, priorizando a definição do comportamento esperado por meio de testes antes da implementação das regras de negócio.

Essa abordagem contribui para a identificação de falhas, validação dos cenários de utilização e manutenção da confiabilidade da aplicação.

## Conceitos aplicados

- Desenvolvimento de APIs REST.
- Programação Orientada a Objetos (POO).
- Arquitetura em camadas.
- Modelagem de entidades e relacionamentos.
- Persistência de dados com JPA.
- Validação de dados e tratamento de exceções.
- Criptografia de senhas com BCrypt.
- Controle de acesso e permissões.
- Test-Driven Development (TDD).
- Testes automatizados das regras de negócio.

## Objetivo do projeto

Consolidar conhecimentos em desenvolvimento backend utilizando Java e Spring Boot, por meio da construção de uma API que simula um cenário real de negócio.

O projeto explora desafios comuns no desenvolvimento de sistemas, como gerenciamento de usuários, controle de permissões, validação de regras de negócio, persistência de dados e garantia de integridade das informações, contribuindo para o desenvolvimento de soluções backend organizadas, testáveis e sustentáveis.
