package request;

import java.util.List;

public class CreateTeamSlideRequest {

    private static class TeamMemberInfo {
        private String jobTitle;
        private String linkedInProfile;

        public TeamMemberInfo() {}

        public TeamMemberInfo(String jobTitle, String linkedInProfile) {
            this.jobTitle = jobTitle;
            this.linkedInProfile = linkedInProfile;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        public String getLinkedInProfile() {
            return linkedInProfile;
        }

        public void setLinkedInProfile(String linkedInProfile) {
            this.linkedInProfile = linkedInProfile;
        }
    }

    private String clientEmail;
    private String clientName;
    private int numTeamMembers;
    private List<TeamMemberInfo> teamMemberInfoList;

    public CreateTeamSlideRequest() {}

    public CreateTeamSlideRequest(String clientEmail, String clientName, int numTeamMembers, List<TeamMemberInfo> teamMemberInfoList) {
        this.clientEmail = clientEmail;
        this.clientName = clientName;
        this.numTeamMembers = numTeamMembers;
        this.teamMemberInfoList = teamMemberInfoList;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getNumTeamMembers() {
        return numTeamMembers;
    }

    public void setNumTeamMembers(int numTeamMembers) {
        this.numTeamMembers = numTeamMembers;
    }

    public List<TeamMemberInfo> getTeamMemberInfoList() {
        return teamMemberInfoList;
    }

    public void setTeamMemberInfoList(List<TeamMemberInfo> teamMemberInfoList) {
        this.teamMemberInfoList = teamMemberInfoList;
    }
}
