require 'aws-sdk'
s3 = Aws::S3::Client.new({
    :access_key_id => 'accessKey1',
    :secret_access_key => 'verySecretKey1',
    :region => 'us-west-2',
    :endpoint => 'http://localhost:8000/',
    :force_path_style => true
}
)
# $bucket_name = 'kotiki'
s3.create_bucket(bucket: 'kotiki', acl:'public-read')

cosmetics_file = File.read('./cosmetics.json')
cosmetics = JSON.parse(cosmetics_file)
cosmetics.each do |cosmetic|
    File.open(cosmetic['path'], 'rb') do |file|
        s3.put_object(bucket: 'kotiki', key: cosmetic['key'], body: file)
        s3.put_object_acl(bucket: 'kotiki', key: cosmetic['key'], acl: 'public-read')
    end
end

# File.open('.\models\body\body_baige.png', 'rb') do |file|
#     s3.put_object(bucket: 'kotiki', key: 'body_baige', body: file)
#     s3.put_object_acl(bucket: 'kotiki', key: 'body_baige', acl: 'public-read')
# end