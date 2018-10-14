import processing.core.*;
import java.util.*;

public class CustomEmptyTeam implements Team{
	
	public String getTeamName(){
    // Nome do Time
		return "AMZN";
	}

  // Lado do Campo
  TeamSide lado_campo;

	public void setTeamSide(TeamSide side){
    lado_campo = side;
	}

	public Robot buildRobot(GameSimulator s, int index){
		return new EmptyRobot(s);
	}
 
	class EmptyRobot extends RobotBasic{
		EmptyRobot(GameSimulator s){
			super(s);
		}

		/*
			Movement methods:
				void setSpeed(FORWARD_SPEED) in m/s
				void setSpeed(FORWARD_SPEED, SIDE_SPEED) in m/s

				void setRotation(ANGULAR_SPEED) in degrees/s

			Extra methods:
				void delay(MS) in miliseconds
				long millis() returns current time in miliseconds since simulation started
		*/

    // Criando um Sensor da Bola
    Sensor sensor_bola;
    
    // Criando um Sensor de distância
    Sensor frente, direita, atras, esquerda;
    
    // Criando um Sensor de Bússola
    Sensor sensor_bussola;
    
    // Lado do Gol
    float lado_gol;
		public void setup(){
			/*
				You should use this method to initialize your code,
				setup Sensors and variables.

				It will be runned once.
			*/
      // Ler o sensor da Bola
      sensor_bola = getSensor("BALL");
      
      // Ler sensores de distância
      frente = getSensor("ULTRASONIC_FRONT");
      direita = getSensor("ULTRASONIC_RIGHT");
      atras = getSensor("ULTRASONIC_BACK");
      esquerda = getSensor("ULTRASONIC_LEFT");
      
      // Ler sensor de bússola
      sensor_bussola = getSensor("COMPASS");
      
      // Definir lado do campo
      if (lado_campo == TeamSide.RIGHT){
         lado_gol = 180f; 
      }
      else {
         lado_gol = 0f; 
      }
		}

		public void loop(){
			/*
				This is the place where you should place the control
				code for your robot. It is called everytime it returns,
				unlimited times.
			*/
      // =================== Sensor Bússola =====================
      // Ler direção do gola
      float direcao_gol = sensor_bussola.readValue(0);
      System.out.println(direcao_gol);
      // ==========================================================
      
      // =================== Movimentos ===========================
      // Primeiro teste com os Robôs da AMZN
      // Simples movimentos, seguindo o tutorial no Youtube
      /*
      // Andar para frente
      setSpeed(1.0f, 0);
      delay(1000);
      stopMotors();
      
      // Andar para esquerda
      setSpeed(0,-1.0f);
      delay(1000);
      stopMotors();
      
      // Andar para trás
      setSpeed(-1.0f, 0);
      delay(1000);
      stopMotors();
      
      // Andar para direita
      setSpeed(0,1.0f);
      delay(1000);
      stopMotors(); 
      // ============================================================
      */
      // =================== Sensor Bola ============================
      // Ler ângulo da bola
      float angulo_bola = sensor_bola.readValue(0);
      
      // Ler distância da bola
      float distancia_bola = sensor_bola.readValue(1);
      
      // Virar o Robô na direção da Bola
      setRotation(angulo_bola * 1.0f);
      
      // Executar movimento por 1s
      delay(1000);
      
      // Se o Robô estiver na frente da Bola, parar
      if (distancia_bola < 0.2f && angulo_bola < 10 && angulo_bola > -10) {
         stopMotors();
         delay(1000);
      }
      else {
         setSpeed(1.0f);
         delay(1000);
      }
      
      // Para os motores do Robô
      stopMotors();      
      // ============================================================
      
      // =================== Sensor Distância =======================
      // Ler valor do sensores
      float distancia_frente = frente.readValue(0);
      float distancia_direita = direita.readValue(0);
      float distancia_atras = atras.readValue(0);
      float distancia_esquerda = esquerda.readValue(0);
      /*
      // Não sair do campo(Área branca)
      if (distancia_direita < 0.1f) {
         stopMotors();
         delay(500);
         setSpeed(0, -2.0f);
         delay(500);
      }
      else {
         setSpeed(0, 2.0f);
         delay(500);
      }
      
      if (distancia_esquerda < 0.1f) {
         stopMotors();
         delay(500);
         setSpeed(0, 2.0f);
         delay(500);
      }
      else {
         setSpeed(0, -2.0f);
         delay(500);
      } */
      // ============================================================
		}

		/*
			If you want to code the thread method yourself, instead of
			using the already made `setup` and `loop` methods, you can
			override the method `run`. Uncomment those lines to use.
		*/
		// public void run(){
			
		// }

		/*
			You can use this method to decorate your robot.
			use Processing methods from the `canvas` object.

			The center of the robot is at [0,0], and the limits
			are 100px x 100px.
		*/
		public void decorateRobot(PApplet canvas){
			
		}

		/*
			Called whenever a robot is:
				PAUSED
				REMOVED from field
				PLACED in field
				STARTED
		*/
		public void onStateChanged(String state){
		}
	}
 
}
