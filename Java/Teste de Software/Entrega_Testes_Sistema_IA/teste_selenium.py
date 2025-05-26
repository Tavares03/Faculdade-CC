
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
import time

# Configurar o Chrome (modo headless opcional)
options = Options()
options.add_argument("--headless")  # Comentar esta linha para ver o navegador

# Caminho do driver
driver = webdriver.Chrome(options=options)

try:
    # Acessar o front-end
    driver.get("http://localhost")  # Porta 80 definida no docker-compose

    # Preencher o formulário
    driver.find_element(By.ID, "name").send_keys("João da Silva")
    driver.find_element(By.ID, "balance").send_keys("1500.00")
    driver.find_element(By.ID, "purchases").send_keys("300.00")
    driver.find_element(By.ID, "cash_advance").send_keys("200.00")
    driver.find_element(By.ID, "credit_limit").send_keys("5000.00")

    # Enviar o formulário
    driver.find_element(By.ID, "avaliacaoForm").submit()

    # Esperar resposta aparecer
    time.sleep(2)

    # Verificar se resultado está presente
    resultado = driver.find_element(By.ID, "resultado").text
    print("Resultado exibido:", resultado)

finally:
    driver.quit()
