package com.example.decoder_app.filter;

/**
 * @author Lokesh chennamchetty
 * @date 11/10/2020
 */
public class FilterConditions {

    private String query;
    private String filterType;
    private int fileLanguageFilter = FileLanguageFilter.ALL;
    private int projectLanguageFilter = ProjectLanguageFilter.ALL;

    private FilterConditions(@FilterType String filterType, String query) {
        this.filterType = filterType;
        this.query = query;
    }

    private void setFileLanguageFilter(@FileLanguageFilter int fileLanguageFilter) {
        this.fileLanguageFilter = fileLanguageFilter;
    }

    private void setProjectLanguageFilter(@FileLanguageFilter int projectLanguageFilter) {
        this.projectLanguageFilter = projectLanguageFilter;
    }

    public String getQuery() {
        return query;
    }

    public String getFilterType() {
        return filterType;
    }

    public int getFileLanguageFilter() {
        return fileLanguageFilter;
    }

    public int getProjectLanguageFilter() {
        return projectLanguageFilter;
    }

    public static class Builder {

        private String query = "";
        private String filterType;
        private int fileLanguageFilter = FileLanguageFilter.ALL;
        private int projectLanguageFilter = ProjectLanguageFilter.ALL;

        public Builder(@FilterType String filterType) {
            this.filterType = filterType;
        }

        public Builder setQuery(String query) {
            this.query = query;
            return this;
        }

        public Builder setFileLanguage(@FileLanguageFilter int fileLanguageFilter) {
            this.fileLanguageFilter = fileLanguageFilter;
            return this;
        }

        public Builder setProjectLanguage(@ProjectLanguageFilter int projectLanguageFilter) {
            this.projectLanguageFilter = projectLanguageFilter;
            return this;
        }

        public FilterConditions build() {
            if (filterType.equals(FilterType.File)) {
                FilterConditions conditions = new FilterConditions(filterType, query);
                conditions.setFileLanguageFilter(fileLanguageFilter);
                return conditions;
            } else {
                FilterConditions conditions = new FilterConditions(filterType, query);
                conditions.setProjectLanguageFilter(projectLanguageFilter);
                return conditions;
            }
        }
    }
}