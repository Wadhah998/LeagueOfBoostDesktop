    package LeagueOfBoost.entities;

import java.util.Date;
    import javafx.scene.control.Button;

    public class Message {

        private int id;
        private int reclamation_id;
        private int user_id;
        private Date date;
        private String message;


            public Message(int reclamation_id, Date date, String message) {
            
            this.reclamation_id=reclamation_id;    
            this.date = date;
            this.message = message;
        }
        
        
        public Message(int id, int reclamation_id, int user_id, Date date, String message) {
            this.id = id;
            this.reclamation_id = reclamation_id;
            this.user_id = user_id;
            this.date = date;
            this.message = message;
        }



   

        public Message(){}

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getReclamation_id() {
            return reclamation_id;
        }

        public void setReclamation_id(int reclamation_id) {
            this.reclamation_id = reclamation_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getMessage() {
            return message;
        }



        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "id=" + id +
                    ", reclamation_id=" + reclamation_id +
                    ", user_id=" + user_id +
                    ", date=" + date +
                    ", message='" + message + '\'' +
                    '}';
        }

    }
