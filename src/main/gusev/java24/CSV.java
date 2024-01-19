package main.gusev.java24;

public class CSV {
    public class InfoModel {
        private String secid;
        private String shortname;
        private String regnumber;
        private String name;
        private String emitent_title;
        private String emitent_inn;
        private String emitent_okpo;
        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append(secid);
            sb.append(shortname);
            sb.append(regnumber);
            sb.append(name);
            sb.append(emitent_title);
            sb.append(emitent_inn);
            sb.append(emitent_okpo);
            return sb.toString();
        }
        public String getSecid() {
            return secid;
        }
        public void setSecid(String secid) {
            this.secid = secid;
        }
        public String getShortname(){
            return  shortname;
        }
        public void setShortname(String shortname) {
            this.shortname = shortname;
        }
        public String getRegnumber() {
            return regnumber;
        }
        public void setRegnumber(String regnumber) {
            this.regnumber = regnumber;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getEmitent_title() {
            return emitent_title;
        }
        public void setEmitent_title(String emitent_title) {
            this.emitent_title = emitent_title;
        }
        public String getEmitent_inn() {
            return emitent_inn;
        }
        public void setEmitent_inn(String emitent_inn) {
            this.emitent_inn = emitent_inn;
        }
        public String getEmitent_okpo() {
            return emitent_okpo;
        }
        public void setEmitent_okpo(String emitent_okpo) {
            this.emitent_okpo = emitent_okpo;
        }
    }
}
