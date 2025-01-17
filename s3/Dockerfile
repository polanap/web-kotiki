FROM ruby:3.3
ENV VERSION=3.0.0.rc3
WORKDIR /tmp
COPY . /tmp/.
RUN gem install aws-sdk
ENTRYPOINT ["ruby", "s3_script.rb"]