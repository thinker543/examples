public class CreateReport {
    public static void main(String[] args) {
        Process process = new Process();
        process.name = "baiyang";
        process.process = "30%";
        process.solvedBugNum = 11;
        process.solvingBugNum = 1;
        process.url = "www.baidu.com";
        new CreateReport().getText(process);
    }
    public void getText(Process process){
        //读取配置，填入process的操作
        System.out.println("测试进度："+process.getProcess());
        System.out.println("已解决bug："+process.getSolvedBugNum());
        System.out.println("未解决bug，"+process.getSolvingBugNum());
        System.out.println("@"+process.getName());
        System.out.println("bug列表"+process.getUrl());

    }
    static class Process{
        private String process;
        private int solvedBugNum;
        private int solvingBugNum;
        private String url;
        private String name;

        public String getProcess() {
            return process;
        }

        public void setProcess(String process) {
            this.process = process;
        }

        public int getSolvedBugNum() {
            return solvedBugNum;
        }

        public void setSolvedBugNum(int solvedBugNum) {
            this.solvedBugNum = solvedBugNum;
        }
        public int getSolvingBugNum() {
            return solvedBugNum;
        }

        public void setSolvingBugNum(int solvingBugNum) {
            this.solvedBugNum = solvingBugNum;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

